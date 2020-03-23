给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。

每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        LinkedList<Integer> list=new LinkedList<>();
        queue.offer(root);
        list.add(0);
        int max=1;
        while(!queue.isEmpty()){
            int size=queue.size();
            while(size!=0){
                TreeNode cur=queue.poll();
                int index=list.remove(0);
                if(cur.left!=null){
                    queue.offer(cur.left);
                    list.add(2*index+1);
                }
                if(cur.right!=null){
                    queue.offer(cur.right);
                    list.add(2*index+2);
                }
                size--;
            }
            if(!list.isEmpty()){
                max=Math.max(max,list.getLast()-list.getFirst()+1);
            }
            
        }
        return max;
    }
}

给定一个二叉树，确定它是否是一个完全二叉树。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if(root==null) return true;
        Queue<TreeNode> queue=new LinkedList<>();
        boolean flag=false;
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode cur=queue.poll();
            if(flag){
                if(cur!=null&&(cur.left!=null||cur.right!=null)){
                    return false;
                }
            }else if(cur.left!=null&&cur.right!=null){
                queue.offer(cur.left);
                queue.offer(cur.right);
            }else{
                flag=true;
                if(cur.left==null&&cur.right!=null){
                    return false;
                }else if(cur.left!=null){
                    queue.offer(cur.left);
                }
            }
        }
        return true;
    }
}

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if(root==null) return true;
        Queue<TreeNode> queue=new LinkedList<>();
        boolean flag=false;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
                TreeNode cur=queue.poll();
            if(flag){
                if(cur!=null){
                    return false;
                }
            }else{
                if(cur==null){
                    flag=true;
                }else{
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
        }
        return true;
    }
}

给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。
class Solution {
    private TreeNode cur;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy=new TreeNode(-1);
        cur=dummy;
        inOrder(root);
        return dummy.right;
    }
    private void inOrder(TreeNode root){
        if(root==null){
            return ;
        }
        inOrder(root.left);
        root.left=null;
        cur.right=root;
        cur=root;
        inOrder(root.right);
    }
}

给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    private int sum;
    public TreeNode convertBST(TreeNode root) {
        if(root==null) return null;
        travel(root);
        return root;
    }
    private void travel(TreeNode root){
        if(root==null){
            return ;
        }
        travel(root.right);
        root.val+=sum;
        sum=root.val;
        travel(root.left);
    }
}
