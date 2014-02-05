class Main {
    public static void printMap(int w, int h) {
        if(w % 2 == 0 || h % 2 == 0) {
            throw new IllegalArgumentException();
        }
        char[] img = new char[w * h];
        int pixelIndex = 0;
        int numSharp=1;
        boolean increasing=true;
        for(int i=0; i<h; i++) {
            for(int j=0; j<(w-numSharp)/2; j++) {
                //System.out.print("#");
                img[pixelIndex++] = '#';
            }
            for(int j=0; j<numSharp; j++) {
                //System.out.print("_");
                img[pixelIndex++] = '_';
            }
            for(int j=0; j<(w-numSharp)/2; j++) {
                //System.out.print("#");
                img[pixelIndex++] = '#';
            }
            if(increasing) {
                numSharp+=2;
            } else {
                numSharp-=2;
            }
            if(numSharp == w) {
                increasing = false;
            } 
        }

        printPixels(img, w, h);
        click('%', 0, 0, img, w, h);
        click('@', 3, 3, img, w, h);
        click('%', 4, 4, img, w, h);
        printPixels(img, w, h);
    }

    //private static int debugCount = 0;

    public static void click(char to, int x, int y, char[] img, int w, int h) {
        final char from = img[y*w+x];
        fill(from, to, x, y, img, w, h);
    }
    public static void fill(char from, char to, int x, int y, char[] img, int w, int h) {
        //if(debugCount >= 4) return;
        //System.out.println(String.format("%s -> %s at (%d, %d)", from, to, x, y));
        //debugCount++;
        if(img[y*w+x] == from) {
            img[y*w+x] = to;
            if(canFill(from, x+1, y, img, w, h)) {
                fill(from, to, x+1, y, img, w, h);
            }
            if(canFill(from, x-1, y, img, w, h)) {
                fill(from, to, x-1, y, img, w, h);
            }
            if(canFill(from, x, y+1, img, w, h)) {
                fill(from, to, x, y+1, img, w, h);
            }
            if(canFill(from, x, y-1, img, w, h)) {
                fill(from, to, x, y-1, img, w, h);
            }
        }
    }
    private static boolean canFill(char target, int x, int y, char[] img, int w, int h) {
        if(x<0 || y<0) return false;
        if(x>=w || y>=h) return false;
        int index = y*w+x;
        if(index >= 0 && index < img.length) {
            return img[index] == target;
        } else {
            return false;
        }
    }
    public static void printPixels(char[] img, int w, int  h) {
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                System.out.print(img[i*w+j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        printMap(5, 5);
    }
}
