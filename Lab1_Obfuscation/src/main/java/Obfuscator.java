public class Obfuscator {

    private int KEY;

    Obfuscator(int key){
        this.KEY = key;
    }

    public String obfuscate(String str){
        return new String(
                str.chars()
                        .map(this::encode)
                        .toArray(),
                0,
                str.length()
        );
    }

    private int encode(int character){
        return character^KEY;
    }
}