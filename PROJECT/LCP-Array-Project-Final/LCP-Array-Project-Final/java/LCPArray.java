import java.util.*;

public class LCPArray {
    static String text;
    static int[] sa, lcp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==============================================");
        System.out.println("                  LCP ARRAY");
        System.out.println(" Compute the Longest Common Prefix array from");
        System.out.println(" a suffix array and visualize the result.");
        System.out.println("==============================================");
        System.out.print("\nEnter a string: ");
        text = sc.nextLine();
        if (text.isEmpty()) { System.out.println("Please enter a non-empty string."); return; }

        sa = buildSuffixArray(text);
        lcp = kasai(text, sa);
        printSuffixes();
        System.out.println("\n========== SUFFIX ARRAY ==========");
        System.out.println(Arrays.toString(sa));
        System.out.println("\n========== LCP ARRAY ==========");
        System.out.println(Arrays.toString(lcp));
        int max = Arrays.stream(lcp).max().orElse(0);
        System.out.println("Longest LCP value: " + max);
        if (max > 0) for (int i=1;i<lcp.length;i++) if(lcp[i]==max){
            System.out.println("Longest repeated substring: " + text.substring(sa[i], sa[i]+max)); break;
        }
        printPatterns();
        long a=System.nanoTime(); int[] n=naive(text,sa); long b=System.nanoTime();
        long c=System.nanoTime(); int[] k=kasai(text,sa); long d=System.nanoTime();
        System.out.println("\n========== PERFORMANCE COMPARISON ==========");
        System.out.printf("Naive LCP : %.3f microseconds%n",(b-a)/1000.0);
        System.out.printf("Kasai LCP : %.3f microseconds%n",(d-c)/1000.0);
        System.out.println("Arrays match: " + Arrays.equals(n,k));
        sc.close();
    }

    static int[] buildSuffixArray(String s) {
        Integer[] x=new Integer[s.length()];
        for(int i=0;i<s.length();i++) x[i]=i;
        Arrays.sort(x,(a,b)->s.substring(a).compareTo(s.substring(b)));
        int[] r=new int[s.length()]; for(int i=0;i<r.length;i++) r[i]=x[i]; return r;
    }
    static int[] naive(String s,int[] sa){
        int[] r=new int[s.length()];
        for(int i=1;i<sa.length;i++){int a=sa[i-1],b=sa[i],k=0;while(a+k<s.length()&&b+k<s.length()&&s.charAt(a+k)==s.charAt(b+k))k++;r[i]=k;}
        return r;
    }
    static int[] kasai(String s,int[] sa){
        int n=s.length(); int[] rank=new int[n],r=new int[n];
        for(int i=0;i<n;i++) rank[sa[i]]=i;
        int k=0;
        for(int i=0;i<n;i++){int q=rank[i];if(q==0){k=0;continue;}int j=sa[q-1];while(i+k<n&&j+k<n&&s.charAt(i+k)==s.charAt(j+k))k++;r[q]=k;if(k>0)k--;}
        return r;
    }
    static void printSuffixes(){
        System.out.println("\n========== SORTED SUFFIXES ==========");
        System.out.printf("%-6s %-10s %-20s %-5s%n","Rank","Index","Suffix","LCP");
        for(int i=0;i<sa.length;i++) System.out.printf("%-6d %-10d %-20s %-5d%n",i,sa[i],text.substring(sa[i]),lcp[i]);
    }
    static void printPatterns(){
        Set<String> p=new LinkedHashSet<>();
        for(int i=1;i<lcp.length;i++) if(lcp[i]>=2) p.add(text.substring(sa[i],sa[i]+lcp[i]));
        System.out.println("\n========== REPEATED / COMMON PATTERNS ==========");
        if(p.isEmpty()) System.out.println("No repeated pattern of length 2 or more."); else p.forEach(System.out::println);
    }
}
