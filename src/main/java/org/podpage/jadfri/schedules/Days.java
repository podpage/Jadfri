package org.podpage.jadfri.schedules;

/**
 * Created by podpage on 28.10.2017.
 */
public enum Days {
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday"),
    SUNDAY("sunday");

    private String day;

    Days(String day) {
        this.day = day;
    }

    public static Days[] getDays(int days) {
        //I know this is weird shit
        String binary = "00000000" + Integer.toBinaryString(days);
        binary = binary.substring(binary.length() - 7, binary.length());
        Days[] dayArray = new Days[binary.replace("0", "").length()];
        char[] chars = binary.toCharArray();
        int c = 0;
        for (int i = 6; i >= 0; i--) {
            if (chars[i] == '1') {
                dayArray[c] = values()[6-i];
                c++;
            }
        }
        return dayArray;
    }

    public String getDay() {
        return day;
    }
}
