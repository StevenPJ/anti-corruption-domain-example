package uk.co.mettle.multiple.fincrime;

public interface FincrimeRepository {
    void save(FincrimeEvent event);
}
