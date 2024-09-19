package com.example.oirms;

public enum Region {
    NORTHERN_UNGUJA("Northern Unguja", new String[]{"Northern A", "Northern B"}),
    SOUTHERN_UNGUJA("Southern Unguja", new String[]{"Central", "Southern"}),
    WESTERN_UNGUJA("Western Unguja", new String[]{"Urban", "West A", "West B"}),
    NORTHERN_PEMBA("Northern Pemba", new String[]{"Wete"}),
    SOUTHERN_PEMBA("Southern Pemba", new String[]{"Chake Chake"});

    private final String name;
    private final String[] districts;

    Region(String name, String[] districts) {
        this.name = name;
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public String[] getDistricts() {
        return districts;
    }
}
