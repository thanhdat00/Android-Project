package com.example.currency_converter;

public class CurrencyConverter {

    private final String input;

    public CurrencyConverter(String s) {
        this.input = s;
    }

    public String sumof2String(String s1, String s2)
    {
        if(s1.equals("BAD EXPRESSION")) return "BAD EXPRESSION";
        if(s2.equals("BAD EXPRESSION")) return "BAD EXPRESSION";
        Double d1=Double.parseDouble(s1);
        Double d2=Double.parseDouble(s2);
        return (String.valueOf(d1+d2));
    }
    public String subof2String(String s1, String s2)
    {
        if(s1.equals("BAD EXPRESSION")) return "BAD EXPRESSION";
        if(s2.equals("BAD EXPRESSION")) return "BAD EXPRESSION";
        Double d1=Double.parseDouble(s1);
        Double d2=Double.parseDouble(s2);
        return (String.valueOf(d1-d2));
    }
    public String mulof2String(String s1, String s2)
    {
        if(s1.equals("BAD EXPRESSION")) return "BAD EXPRESSION";
        if(s2.equals("BAD EXPRESSION")) return "BAD EXPRESSION";
        Double d1=Double.parseDouble(s1);
        Double d2=Double.parseDouble(s2);
        return (String.valueOf(d1*d2));
    }
    public String divof2String(String s1, String s2)
    {
        if(s1.equals("BAD EXPRESSION")) return "BAD EXPRESSION";
        if(s2.equals("BAD EXPRESSION")) return "BAD EXPRESSION";
        Double d1=Double.parseDouble(s1);
        Double d2=Double.parseDouble(s2);
        if (d2==0)
            return "BAD EXPRESSION";
        return (String.valueOf(d1/d2));
    }
    public boolean valid(String input){
        for (int i=0;i<input.length()-1;i++){
            char c1=input.charAt(i), c2=input.charAt(i+1);
            String check1=String.valueOf(c1), check2= String.valueOf(c2);
            if ((check1.equals("+")||check1.equals("-")||check1.equals("x")||check1.equals(":"))&&
                    (check2.equals("+")||check2.equals("-")||check2.equals("x")||check2.equals(":")))
                return false;
        }
        String check3=input.substring(0,1);
        String check4=input.substring(input.length()-1);
        if (check3.equals("+")||check3.equals("-")||check3.equals("x")||check3.equals(":"))
            return false;
        if (check4.equals("+")||check4.equals("-")||check4.equals("x")||check4.equals(":"))
            return false;
        return true;
    }
    public String calculate(String input) {
        if (!valid(input)) return "BAD EXPRESSION";
        for (int i = input.length() - 1; i >= 0; i--) {
            char c = input.charAt(i);
            String s = String.valueOf(c);
            if (s.equals("+")) {
                String s1, s2;
                s1 = input.substring(0, i);
                s2 = input.substring(i + 1);
                return (sumof2String(calculate(s1), calculate(s2)));
            } else if (s.equals("-")) {
                String s1, s2;
                s1 = input.substring(0, i);
                s2 = input.substring(i + 1);
                return (subof2String(calculate(s1), calculate(s2)));
            }
        }
        for (int i = input.length() - 1; i >= 0; i--) {
            char c = input.charAt(i);
            String s = String.valueOf(c);
            if (s.equals("x")) {
                String s1, s2;
                s1 = input.substring(0, i);
                s2 = input.substring(i + 1);
                return (mulof2String(calculate(s1), calculate(s2)));
            } else if (s.equals(":")) {
                String s1, s2;
                s1 = input.substring(0, i);
                s2 = input.substring(i + 1);
                return (divof2String(calculate(s1), calculate(s2)));
            }
        }
        return input;
    }
}
