package models;

public class Loan {

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getLoanStateId() {
        return loanStateId;
    }

    public void setLoanStateId(int loanStateId) {
        this.loanStateId = loanStateId;
    }

    public int getLoanOverdueId() {
        return loanOverdueId;
    }

    public void setLoanOverdueId(int loanOverdueId) {
        this.loanOverdueId = loanOverdueId;
    }

    public int getYearlyOverdueId() {
        return yearlyOverdueId;
    }

    public void setYearlyOverdueId(int yearlyOverdueId) {
        this.yearlyOverdueId = yearlyOverdueId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String getLoanStateName() {
        return loanStateName;
    }

    public void setLoanStateName(String loanStateName) {
        this.loanStateName = loanStateName;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
    
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPendingDays() {
        return pendingDays;
    }

    public void setPendingDays(int pendingDays) {
        this.pendingDays = pendingDays;
    }
    
    private int loanId;
    private int loanStateId;
    private String loanStateName;
    private String responsable;
    private String admin;
    private int loanOverdueId;
    private int yearlyOverdueId;
    private String userId;
    private String adminId;
    private String startDate;
    private String endDate;
    private String price;
    private Integer pendingDays;
}
