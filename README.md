# Anti corruption domain example

This repo is a short demo of the impact an anti corruption domain can have on blast radius of changes.

## Scenario

This scenario imagines two services:
* Payments: responsible for checking a payment for fraud and completing payments.
* Fincrime: responsible for checking fraud in payments.

The Payments team receives a message which contains lots of low level fields. The fincrime team wants to consume
events with those fields so it can do async fraud checks.

## Example 1: Single Domain

In this scenario, there is:
* a Payments domain
* A fincrime client -> for sync checks
* A payment orchestrator, which calls the fincrime check before updating the payment domain

As there is a single aggregate, the payment domain needs to save all the fields needed so fincrime can consume them in the async flow. As this event is produced by the entity saved to the database, any new requirements from fincrime will lead to an event migration, and changes to the payment domain.

A benefit of this approach is that the Fincrime sync code is lightweight (a single interface), so any changes to the sync flow only really happen in the call in the orchestrator. 

## Example 2: Anti corruption Domain
In this scenario, there is:
* a Payments domain
* A Fincrime domain
* A payment orchestrator, which calls the fincrime domain before updating the payment domain

Here each domain has its own repository, and all the fincrime specific fields can be moved from the payment domain to the fincrime domain. The fincrime domain makes the call to the fincrime client, but also will persist an entity with the additional fincrime fields used by the async flow.

The benefit of this approach is any changes to fincrime requirements will not impact the Payment domain, and the payment domain becomes more lightweight as it doesnt need to worry about persisting those fields which are not used as part of its invariant protection logic.

The trade off is there are additional files, and a new domain to maintain. It also pushes complexity into the infrastructure (repository). If both domains are happening in a single process, and we want to optimise for minimal DB inserts, we would use something like an in process cache to store the intermediate events, before persisting the information together.

## Summary

Seperating concerns into seperate aggregates/domains can lead to purer domains and reduce the blast radius of changes. In this scenario, where an incoming message has a lot of data that other consumers care about, publishing that as its own event can allow other teams to build their own projections without adding toil for the team supporting the payments domain. This can prevent costly migrations that occur if the payments domain model is coupled to the event consumed from other domains.

You can see the impact of making changes to both the [sync flow](https://github.com/StevenPJ/anti-corruption-domain-example/commit/aea5c21959227589911abd7fcfcfb16ec150a38e) and the [async flow](https://github.com/StevenPJ/anti-corruption-domain-example/commit/41069a8532a73d3576e462649103ae6094b12f57)
to see the differences in blast radius.