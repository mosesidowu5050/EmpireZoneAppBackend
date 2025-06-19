package org.mosesidowu.empirezone.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("ads_db")
public class Ad {

    @Id
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private String location;
    private String categoryId;
    private String postedByUserId;
    private boolean isActive;
    private LocalDateTime postedAt;

}
