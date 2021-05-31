package com.epam.jwd.final_task.model;

import java.time.LocalDate;
import java.util.Objects;

public class Subscription implements DbEntity {
    private Long id;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Subscription(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public void setId(Long id) {
        if (this.getId() != null) {
            throw new IllegalStateException("id already assigned");
        }
        this.id = id;
    }

    public Subscription updateStartDate(LocalDate newStartDate) {
        if (newStartDate == null) {
            throw new NullPointerException();
        }
        final Subscription newSubscription = new Subscription(newStartDate, endDate);
        newSubscription.setId(id);
        return newSubscription;
    }

    public Subscription updateEndDate(LocalDate newEndDate) {
        if (newEndDate == null) {
            throw new NullPointerException();
        }
        final Subscription newSubscription = new Subscription(startDate, newEndDate);
        newSubscription.setId(id);
        return newSubscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
