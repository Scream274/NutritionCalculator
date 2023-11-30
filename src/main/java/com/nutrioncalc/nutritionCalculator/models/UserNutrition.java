package com.nutrioncalc.nutritionCalculator.models;

import com.nutrioncalc.nutritionCalculator.models.enums.Gender;
import com.nutrioncalc.nutritionCalculator.models.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
public class UserNutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String fullName;
    @Column(length = 100, unique = true)
    @NotBlank(message = "Email cant be empty")
    @Email
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "Password cant be empty")
    private String password;
    private Integer weight;
    private Integer height;
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private Gender gender = Gender.OTHER;
    private Double BMI;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<DailyStat> dailyStatistic;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "person_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = Set.of(Role.USER);

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UserNutrition that = (UserNutrition) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public void calculateBMI() {
        if (height != null && height > 0 && weight != null && weight > 0) {
            double heightInMeters = height / 100.0;
            BMI = Math.round((weight / (heightInMeters * heightInMeters)) * 100.0) / 100.0;
        } else {
            BMI = null;
        }
    }
}
