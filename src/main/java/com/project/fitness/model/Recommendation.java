package com.project.fitness.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String type;


    // in SQL then char limit is 250 we are modifying the limit
    // upto 2k using column annotation
    @Column(length = 2000)
    private String recommendation;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> improvements;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> suggestions;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> safety;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "activity_id",nullable = false,foreignKey = @ForeignKey(name = "fk_recommendation_activity"))
    @JsonIgnore
    private Activity activity;



    // lazy mean if user fetch it not fetch recommendation
    // until user fetch it
    //eager means when user fetch recommendation will fetch automatically
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false,foreignKey = @ForeignKey(name = "fk_recommendation_user"))
    @JsonIgnore
    private User user;
}
