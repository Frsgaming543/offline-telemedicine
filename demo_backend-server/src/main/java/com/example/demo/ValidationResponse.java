package com.example.demo;

public class ValidationResponse {
    private String status;
    private String message;
    private String actionRequired;
    private String recommendedGenericAlternative;

    public ValidationResponse(String status, String message, String actionRequired, String recommendedGenericAlternative) {
        this.status = status;
        this.message = message;
        this.actionRequired = actionRequired;
        this.recommendedGenericAlternative = recommendedGenericAlternative;
    }

    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getActionRequired() { return actionRequired; }
    public void setActionRequired(String actionRequired) { this.actionRequired = actionRequired; }

    public String getRecommendedGenericAlternative() { return recommendedGenericAlternative; }
    public void setRecommendedGenericAlternative(String recommendedGenericAlternative) { this.recommendedGenericAlternative = recommendedGenericAlternative; }


    
}
