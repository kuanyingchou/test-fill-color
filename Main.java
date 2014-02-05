import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

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
                img[pixelIndex++] = '#';
            }
            for(int j=0; j<numSharp; j++) {
                img[pixelIndex++] = '_';
            }
            for(int j=0; j<(w-numSharp)/2; j++) {
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

    public static void click(char to, int x, int y, char[] img, int w, int h) {
        final char from = img[y*w+x];
        fill(from, to, x, y, img, w, h);
    }

    static class Px {
        public int x, y;
        public Px(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    public static void fill(char from, char to, int x, int y, char[] img, int w, int h) {
        //final Queue<Px> pixels = new LinkedList<Px>();
        final Stack<Px> pixels = new Stack<Px>();
        pixels.push(new Px(x, y));

        while(pixels.size() > 0) {
            final Px p = pixels.pop();
            if(canFill(from, p.x, p.y, img, w, h)) {
                img[p.y*w+p.x] = to;
                pixels.push(new Px(p.x+1, p.y));
                pixels.push(new Px(p.x-1, p.y));
                pixels.push(new Px(p.x, p.y+1));
                pixels.push(new Px(p.x, p.y-1));
            }
        }
    }
    private static boolean canFill(char target, int x, int y, char[] img, int w, int h) {
        if(x<0 || y<0) return false;
        if(x>=w || y>=h) return false;
        int index = y*w+x;
        if(inBounds(index, img)) {
            return img[index] == target;
        } else {
            return false;
        }
    }
    private static boolean inBounds(int index, char[] img) {
        return index >= 0 && index < img.length;
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
        //printMap(3, 3);
        printMap(5, 5);
        //printMap(513, 513);
    }
}
