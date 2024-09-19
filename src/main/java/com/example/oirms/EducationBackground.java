package com.example.oirms;

import jakarta.persistence.*;
import java.time.Year;

@Entity
public class EducationBackground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Primary education fields
    private String primarySchoolName;
    private String primarySchoolRegion;
    private String primarySchoolDistrict;
    private Year primaryStartYear;
    private Year primaryEndYear;

    // Secondary education fields
    private String secondarySchoolName;
    private String secondarySchoolRegion;
    private String secondarySchoolDistrict;
    private Year secondaryStartYear;
    private Year secondaryEndYear;
    private String formFourIndexNumber;

    // Advanced education fields
    private String formSixSchoolName;
    private Year formSixStartYear;
    private Year formSixEndYear;
    private String formSixIndexNumber;

    // Diploma education fields
    private String diplomaCollegeName;
    private String diplomaCourseName;
    private Year diplomaStartYear;
    private Year diplomaEndYear;
    private String diplomaRegistrationNumber;

    // Higher education fields
    private String higherEducationCollegeName;
    private String higherEducationCourse;
    private Year higherEducationStartYear;
    private Year higherEducationEndYear;
    private String higherEducationRegistrationNumber;

    // Chart file paths
    private String formFourChartFilePath;
    private String formSixChartFilePath;
    private String diplomaChartFilePath;
    private String higherEducationChartFilePath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimarySchoolName() {
        return primarySchoolName;
    }

    public void setPrimarySchoolName(String primarySchoolName) {
        this.primarySchoolName = primarySchoolName;
    }

    public String getPrimarySchoolRegion() {
        return primarySchoolRegion;
    }

    public void setPrimarySchoolRegion(String primarySchoolRegion) {
        this.primarySchoolRegion = primarySchoolRegion;
    }

    public String getPrimarySchoolDistrict() {
        return primarySchoolDistrict;
    }

    public void setPrimarySchoolDistrict(String primarySchoolDistrict) {
        this.primarySchoolDistrict = primarySchoolDistrict;
    }

    public Year getPrimaryStartYear() {
        return primaryStartYear;
    }

    public void setPrimaryStartYear(Year primaryStartYear) {
        this.primaryStartYear = primaryStartYear;
    }

    public Year getPrimaryEndYear() {
        return primaryEndYear;
    }

    public void setPrimaryEndYear(Year primaryEndYear) {
        this.primaryEndYear = primaryEndYear;
    }

    public String getSecondarySchoolName() {
        return secondarySchoolName;
    }

    public void setSecondarySchoolName(String secondarySchoolName) {
        this.secondarySchoolName = secondarySchoolName;
    }

    public String getSecondarySchoolRegion() {
        return secondarySchoolRegion;
    }

    public void setSecondarySchoolRegion(String secondarySchoolRegion) {
        this.secondarySchoolRegion = secondarySchoolRegion;
    }

    public String getSecondarySchoolDistrict() {
        return secondarySchoolDistrict;
    }

    public void setSecondarySchoolDistrict(String secondarySchoolDistrict) {
        this.secondarySchoolDistrict = secondarySchoolDistrict;
    }

    public Year getSecondaryStartYear() {
        return secondaryStartYear;
    }

    public void setSecondaryStartYear(Year secondaryStartYear) {
        this.secondaryStartYear = secondaryStartYear;
    }

    public Year getSecondaryEndYear() {
        return secondaryEndYear;
    }

    public void setSecondaryEndYear(Year secondaryEndYear) {
        this.secondaryEndYear = secondaryEndYear;
    }

    public String getFormFourIndexNumber() {
        return formFourIndexNumber;
    }

    public void setFormFourIndexNumber(String formFourIndexNumber) {
        this.formFourIndexNumber = formFourIndexNumber;
    }

    public String getFormSixSchoolName() {
        return formSixSchoolName;
    }

    public void setFormSixSchoolName(String formSixSchoolName) {
        this.formSixSchoolName = formSixSchoolName;
    }

    public Year getFormSixStartYear() {
        return formSixStartYear;
    }

    public void setFormSixStartYear(Year formSixStartYear) {
        this.formSixStartYear = formSixStartYear;
    }

    public Year getFormSixEndYear() {
        return formSixEndYear;
    }

    public void setFormSixEndYear(Year formSixEndYear) {
        this.formSixEndYear = formSixEndYear;
    }

    public String getFormSixIndexNumber() {
        return formSixIndexNumber;
    }

    public void setFormSixIndexNumber(String formSixIndexNumber) {
        this.formSixIndexNumber = formSixIndexNumber;
    }

    public String getDiplomaCollegeName() {
        return diplomaCollegeName;
    }

    public void setDiplomaCollegeName(String diplomaCollegeName) {
        this.diplomaCollegeName = diplomaCollegeName;
    }

    public String getDiplomaCourseName() {
        return diplomaCourseName;
    }

    public void setDiplomaCourseName(String diplomaCourseName) {
        this.diplomaCourseName = diplomaCourseName;
    }

    public Year getDiplomaStartYear() {
        return diplomaStartYear;
    }

    public void setDiplomaStartYear(Year diplomaStartYear) {
        this.diplomaStartYear = diplomaStartYear;
    }

    public Year getDiplomaEndYear() {
        return diplomaEndYear;
    }

    public void setDiplomaEndYear(Year diplomaEndYear) {
        this.diplomaEndYear = diplomaEndYear;
    }

    public String getDiplomaRegistrationNumber() {
        return diplomaRegistrationNumber;
    }

    public void setDiplomaRegistrationNumber(String diplomaRegistrationNumber) {
        this.diplomaRegistrationNumber = diplomaRegistrationNumber;
    }

    public String getHigherEducationCollegeName() {
        return higherEducationCollegeName;
    }

    public void setHigherEducationCollegeName(String higherEducationCollegeName) {
        this.higherEducationCollegeName = higherEducationCollegeName;
    }

    public String getHigherEducationCourse() {
        return higherEducationCourse;
    }

    public void setHigherEducationCourse(String higherEducationCourse) {
        this.higherEducationCourse = higherEducationCourse;
    }

    public Year getHigherEducationStartYear() {
        return higherEducationStartYear;
    }

    public void setHigherEducationStartYear(Year higherEducationStartYear) {
        this.higherEducationStartYear = higherEducationStartYear;
    }

    public Year getHigherEducationEndYear() {
        return higherEducationEndYear;
    }

    public void setHigherEducationEndYear(Year higherEducationEndYear) {
        this.higherEducationEndYear = higherEducationEndYear;
    }

    public String getHigherEducationRegistrationNumber() {
        return higherEducationRegistrationNumber;
    }

    public void setHigherEducationRegistrationNumber(String higherEducationRegistrationNumber) {
        this.higherEducationRegistrationNumber = higherEducationRegistrationNumber;
    }

    public String getFormFourChartFilePath() {
        return formFourChartFilePath;
    }

    public void setFormFourChartFilePath(String formFourChartFilePath) {
        this.formFourChartFilePath = formFourChartFilePath;
    }

    public String getFormSixChartFilePath() {
        return formSixChartFilePath;
    }

    public void setFormSixChartFilePath(String formSixChartFilePath) {
        this.formSixChartFilePath = formSixChartFilePath;
    }

    public String getDiplomaChartFilePath() {
        return diplomaChartFilePath;
    }

    public void setDiplomaChartFilePath(String diplomaChartFilePath) {
        this.diplomaChartFilePath = diplomaChartFilePath;
    }

    public String getHigherEducationChartFilePath() {
        return higherEducationChartFilePath;
    }

    public void setHigherEducationChartFilePath(String higherEducationChartFilePath) {
        this.higherEducationChartFilePath = higherEducationChartFilePath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getters and setters...
    
}
