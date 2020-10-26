package com.company;

// Ternary Search Trie
public class TST <Value>
{
    private Node root;

    private class Node
    {
        private Value value;
        private char c;
        private Node left;
        private Node middle;
        private Node right;
    }

    public void put( String key, Value value )
    {
        root = put(root, key, value, 0);
    }

    private Node put( Node x, String key, Value value, int d)
    {
        char c = key.charAt(d);
        if(x == null)
        {
            x = new Node();
            x.c = c;
        }
        if(c < x.c)
        {
            x.left = put(x.left, key, value, d);
        }
        else if (c > x.c)
        {
            x.right = put(x.right, key, value, d);
        }
        else if(d < key.length() - 1)
        {
            x.middle = put( x.middle, key, value, d + 1);
        }
        else
            x.value = value;
        return x;
    }

    public Value get(String key)
    {
        Node x = get(root, key, 0);
        if(x == null)
            return null;
        return x.value;
    }

    private Node get(Node x, String key, int d)
    {
        if(x == null)
            return null;
        char c = key.charAt(d);
        if(c < x.c)
        {
            return get(x.left, key, d);
        }
        else if(c > x.c)
        {
            return get(x.right, key, d);
        }
        else if( d < key.length() - 1 )
        {
            return get(x.middle, key, d + 1);
        }
        else
            return x;
    }

    public boolean contains(String key)
    {
        return get(key) != null;
    }
}
