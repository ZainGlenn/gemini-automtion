package com.gemini.util.components.table.model;

import com.codeborne.selenide.Condition;
import com.gemini.util.components.table.TableColumn;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;

public class PatientVisit extends TableData {

    @TableColumn(idx = 0)
    private String avatar;
    @TableColumn(idx = 1, parser = PatientNameParser.class)
    private String patient;
    @TableColumn(idx = 2)
    private String apptTime;
    @TableColumn(idx = 3)
    private String rendering;
    @TableColumn(idx = 4)
    private String visitTypeAndReason;
    @TableColumn(idx = 5)
    private String actions;

    public String getAvatar() {
        return avatar;
    }

    public String getPatient() {
        return patient;
    }

    public String getRendering() {
        return rendering;
    }

    public String getApptTime() {
        return apptTime;
    }

    public String getVisitTypeAndReason() {
        return visitTypeAndReason;
    }

    public String getActions() {
        return actions;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRendering(String rendering) {
        this.rendering = rendering;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public void setApptTime(String apptTime) {
        this.apptTime = apptTime;
    }

    public void setVisitTypeAndReason(String visitTypeAndReason) {
        this.visitTypeAndReason = visitTypeAndReason;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientVisit that = (PatientVisit) o;
        return Objects.equals(avatar, that.avatar) &&
                Objects.equals(patient, that.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(avatar, patient, rendering, apptTime, visitTypeAndReason, actions);
    }

    @Override
    public String toString() {
        return "PatientVisit{" +
                "avatar='" + avatar + '\'' +
                ", patient='" + patient + '\'' +
                ", apptTime='" + apptTime + '\'' +
                ", rendering='" + rendering + '\'' +
                ", visitTypeAndReason='" + visitTypeAndReason + '\'' +
                ", actions='" + actions + '\'' +
                '}';
    }

    public void clickOnNotesButton() {
        getColumnElements().get(6).find(By.xpath(".//span[contains(@id, 'notes')]")).shouldBe(Condition.appear).click();
    }

    public void selectPatient() {
        getColumnElements().get(1).find(By.xpath(".//div[.='" + patient + "']")).shouldBe(Condition.appear).click();
    }

    public void isAvatarLocked() {
        getColumnElements().get(0).find(By.id("consent-lock")).shouldBe(Condition.appear);
    }

    public void isAvatarNotLocked() {
        getColumnElements().get(0).find(By.id("consent-lock")).shouldNotBe(Condition.appear);

    }

    public void checkLocation(String Location) {
        getColumnElements().get(4).shouldBe(Condition.text(visitTypeAndReason));
    }

    public void isUnsignedResults() {
        getColumnElements().get(5).find(By.xpath(".//button[contains(@class,'unsigned-results-btn')]")).shouldBe(Condition.appear);
    }

    public void hoverOverResultsBadge() {
        getColumnElements().get(5).find(By.xpath(".//button[contains(@class,'unsigned-results-btn')]")).shouldBe(Condition.appear).hover();
        $(By.xpath(".//div[contains(@class,'v-tooltip__content menuable__content__active')]//span[contains(text(),'Unsigned Results')]"))
                .shouldHave(Condition.appear);
    }

    public void clickResultsBadge() {
        getColumnElements().get(5).find(By.xpath(".//button[contains(@class,'unsigned-results-btn')]")).shouldBe(Condition.appear).click();
    }

    public int getResultBadgeCount() {
        if ($(By.xpath(".//span[contains(@class,'v-badge__badge red darken-4')]//div")).exists()) {
            String countStr = $(By.xpath(".//span[contains(@class,'v-badge__badge red darken-4')]//div")).getText();
            return Integer.parseInt(countStr);
        }
        return 0;
    }

    public void checkResultBadgeCount(String count) {
        getColumnElements().get(5).find(By.xpath(".//div[contains(@id, 'unsigned-results')]/..//span[contains(@class, 'v-badge__badge red darken-4')]//div[contains(text(),'" + count + "')]"))
                .shouldBe(Condition.appear);
    }

    public void checkClinicalNotesBadgeCount(String count) {
        getColumnElements().get(5).find(By.xpath(".//div[contains(@id, 'active-notes')]/..//span[contains(@class, 'v-badge__badge red darken-4')]//div[contains(text(),'" + count + "')]"))
                .shouldBe(Condition.appear);
    }

}
