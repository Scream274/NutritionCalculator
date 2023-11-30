package com.nutrioncalc.nutritionCalculator.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DailyStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_ref")
    private UserNutrition user;

    private LocalDate date;

    @OneToMany(
            mappedBy = "dailyStat",
            cascade = {PERSIST, MERGE, REFRESH, DETACH},
            orphanRemoval = true)
    @ToString.Exclude
    @Setter(AccessLevel.PRIVATE)
    private List<DishIngredient> dishIngredients = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DailyStat dailyStat = (DailyStat) o;
        return getId() != null && Objects.equals(getId(), dailyStat.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public void addDishIngredients(List<DishIngredient> dishIngredients) {
        this.dishIngredients.addAll(dishIngredients);
    }
}
