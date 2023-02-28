package de.volkswagen.fakultaet73.shoppingsystem.enities;

public enum Category {
    GARDEN("Garten"),
    TECHNOLOGY("Technologie"),
    CONSTRUCTION("Baumaterialien"),
    NON("Undefiniert");

    private final String germanTitle;
    private Category(String germanTitle){
        this.germanTitle = germanTitle;
    }
    public String getGermanTitle(){
        return this.germanTitle;
    }
    public static Category germanNameToEnum (String title){
        switch (title) {
            case "Garten":
                return GARDEN;
            case "Technologie":
                return TECHNOLOGY;
            case "Baumaterialien":
                return CONSTRUCTION;
            default:
                return NON;
        }
    }
}
