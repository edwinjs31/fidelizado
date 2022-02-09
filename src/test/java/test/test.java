package test;

import org.apache.commons.lang3.RandomStringUtils;



/**
 *
 * @author Edwin Jaldin S.
 */
public class test {

    public static void main(String[] args) {
        String c1 =RandomStringUtils.randomAlphanumeric(3).toUpperCase();
        String c2 =RandomStringUtils.randomAlphanumeric(3).toUpperCase();
        System.out.println(c1+"-"+c2);
    }

}
