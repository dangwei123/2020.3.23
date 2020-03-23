给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
class Solution {
    public String decodeString(String s) {
        Stack<String> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c!=']'){
                stack.push(c+"");
            }else{
                String m="";
                while(!stack.isEmpty()&&!stack.peek().equals("[")){
                    m=stack.pop()+m;
                }
                stack.pop();

                String n="";
                while((!stack.isEmpty())&&(stack.peek().charAt(0)>='0'&&stack.peek().charAt(0)<='9')){
                    n=stack.pop()+n;
                }
                String tmp="";
                int count =Integer.valueOf(n);
                while(count--!=0){
                    tmp=m+tmp;
                }
                stack.push(tmp);
             }
        }
        String res="";
        while(!stack.isEmpty()){
            res=stack.pop()+res;
        }
        return res;
    }
}

有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。

给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。

为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。

最后返回经过上色渲染后的图像。
class Solution {
    private int row;
    private int col;
    private Set<Integer> set;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        set=new HashSet<>();
        row=image.length;
        if(row==0) return image;
        col=image[0].length;
        dfs(image,sr,sc,image[sr][sc],newColor);
        return image;
    }
    private void dfs(int[][] image,int i,int j,int target,int newColor){
        if(i<0||j<0||i>=row||j>=col||set.contains(i*col+j)||image[i][j]!=target){
            return ;
        }
        image[i][j]=newColor;
        set.add(i*col+j);
        dfs(image,i+1,j,target,newColor);
        dfs(image,i-1,j,target,newColor);
        dfs(image,i,j+1,target,newColor);
        dfs(image,i,j-1,target,newColor);
    }
}

