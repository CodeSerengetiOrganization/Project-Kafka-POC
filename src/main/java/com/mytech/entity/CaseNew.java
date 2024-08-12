package com.mytech.entity;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class CaseNew {

    private int caseId;

    private CaseStatus caseStatus;

    private CaseType caseType;

    private String createdBy;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    private LocalDateTime pendingReviewDate;

    private String note;

    // Getters and Setters
    // ...
}

