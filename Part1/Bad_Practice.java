public class Bad_Practice {
    public static void main(String[] args){
        String s = "67";
        String h = s;
        if(s == h) {
            System.out.println("They are equal!");
        }
    }
}
