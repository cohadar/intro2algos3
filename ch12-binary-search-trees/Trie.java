import java.util.*;
import java.io.*;
import java.util.function.*;

/**
  * @author Mighty Cohadar 
  */
public class Trie<V> { 

	public static class Alphabet {
		final int size;
		final char first;
		final char last;
		public Alphabet(char first, char last) {
			assert first <= last;
			this.size = last - first + 1;
			this.first = first;
			this.last = last;
			// sanity check
			for (int i = 0; i < this.size; i++) {
				if (i != this.toIndex(this.toChar(i))) {
					throw new IllegalStateException(String.format("alphabet toIndex(toChar(i)) failed for index: %d -> '%c'", i, this.toChar(i)));
				}
			}			
		}
		public int toIndex(char c) {
			return c - first;
		} 
		public char toChar(int index) {
			return (char)(index + first);
		}		
	}

	final Alphabet alphabet;
	final String prefix;
	final Node root;

	private static class Node {
		Object value;
		Node[] children;
	}

	private Trie(Alphabet alphabet, String prefix, Node root) {
		this.alphabet = alphabet;		
		this.prefix = prefix;
		this.root = root;
	}	

	private Trie(Alphabet alphabet, String prefix) {
		this(alphabet, prefix, new Node());
	}

	public Trie(Alphabet alphabet) {
		this(alphabet, "", new Node());
	}

	@SuppressWarnings("unchecked")
	public V put(String key, V value) {
		Node parent = root;
		for (int i = 0; i < key.length(); i++) {
			int index = alphabet.toIndex(key.charAt(i));
			if (parent.children == null) {
				parent.children = new Node[alphabet.size];
			}
			Node child = parent.children[index];
			if (child == null) {
				child = parent.children[index] = new Node();
			}
			parent = child;
		}
		Object ret = parent.value;
		parent.value = value;
		return (V)ret;
	}

	@SuppressWarnings("unchecked")
	public V get(String key) {
		Node parent = root;
		for (int i = 0; i < key.length(); i++) {
			int index = alphabet.toIndex(key.charAt(i));
			if (parent.children == null) {
				parent.children = new Node[alphabet.size];
			}			
			Node child = parent.children[index];
			if (child == null) {
				return null;
			}
			parent = child;
		}
		return (V)parent.value;
	}

	public Trie<V> subTrie(String keyPrefix) {
		if (keyPrefix == null) {
			throw new NullPointerException("keyPrefix");
		}
		if ("".equals(keyPrefix)) {
			return this;
		}
		Node parent = root;
		for (int i = 0; i < keyPrefix.length(); i++) {
			int index = alphabet.toIndex(keyPrefix.charAt(i));
			if (parent.children == null) {
				parent.children = new Node[alphabet.size];
			}			
			Node child = parent.children[index];
			if (child == null) {
				return null;
			}
			parent = child;
		}
		if (parent == null) {
			return null;
		}
		return new Trie<V>(alphabet, prefix + keyPrefix, parent);
	}

	// lexicographic traversal
	public void forEach(BiConsumer<String, V> biConsumer) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		forEach(biConsumer, sb, root);
	}

	@SuppressWarnings("unchecked")
	private void forEach(BiConsumer<String, V> biConsumer, StringBuilder sb, Node parent) { 
		if (parent.value != null) {
			biConsumer.accept(sb.toString(), (V)parent.value);
		}
		if (parent.children == null) {
			return;
		}
		for (int i = 0; i < alphabet.size; i++) {
			Node child = parent.children[i];
			if (child != null) {
				int len = sb.length();		
				sb.append(alphabet.toChar(i));
				forEach(biConsumer, sb, child);
				sb.setLength(len);
			}	
		}
	}

}
