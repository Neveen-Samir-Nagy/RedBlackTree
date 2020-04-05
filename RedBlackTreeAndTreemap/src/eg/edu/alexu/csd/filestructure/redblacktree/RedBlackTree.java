package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.AbstractMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import javax.management.RuntimeErrorException;

public class RedBlackTree<T extends Comparable<T>, V> implements IRedBlackTree<T , V> {

	private INode<T, V> root = new Node<T,V>(null, null, null, null, null, false);
	private INode<T,V> node = root;
    int size =0;
    public  RedBlackTree() {
		size =0;
		clear();
	}
	@Override
	public INode<T,V> getRoot() {
		
		return root;
	}
	

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (root.isNull()) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		root = new Node<T, V>(null,null,null,null,null,false);
	}
	@Override
	public V search(T key) {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new RuntimeErrorException(null);
		}
		INode<T,V> node = root;
		while (!node.isNull()) {
			if (node.getKey().compareTo((T) key) == 0) {
				break;
			} else if (key.compareTo(node.getKey()) > 0) {
				node = node.getRightChild();
			} else {
				node = node.getLeftChild();
			}
		}
		return node.getValue();
	}
	public T ceilingKey(T key) {
		node = root;
		while (!node.isNull()) {
			if (node.getKey().compareTo(key) == 0) {
				return (T) node.getKey();
			} else if (key.compareTo(node.getKey()) > 0) {
				node = node.getRightChild();
			} else {
				if (node.getLeftChild().isNull()) {
					return (T) node.getKey();
				} else {
					node = node.getLeftChild();
				}
			}
		}
		if( node == root && (key.compareTo(node.getKey()) < 0)) {
			return (T) root.getKey();
		}

		return null;
	}
	public Map.Entry<T, V> ceilingEntry(T key) {
		Map.Entry<T, V> e ;
		node = getRoot();
		while (!node.isNull()) {
			if (node.getKey().equals(key)) {
				e= new AbstractMap.SimpleEntry<T, V>((T)node.getKey(),(V) node.getValue());
				return e;
			} else if (key.compareTo(node.getKey()) > 0) {
				node = node.getRightChild();
			} else {
				if (node.getLeftChild().isNull()) {
					e= new AbstractMap.SimpleEntry<T, V>((T)node.getKey(),(V) node.getValue());
					return e;
				} else {
					node = node.getLeftChild();
				}
			}
		}

		return null;
	}

	public Map.Entry<T, V> floorEntry(T key) {
		Map.Entry<T, V> e;
		INode<T,V> node = root;
		while (!node.isNull()) {
			if (node.getKey().equals(key)) {
				e= new AbstractMap.SimpleEntry<T, V>((T)node.getKey(),(V) node.getValue());
				return e;
			} else if (key.compareTo(node.getKey()) > 0) {
				if (node.getRightChild().isNull()) {
					e= new AbstractMap.SimpleEntry<T, V>((T)node.getKey(),(V) node.getValue());
							return e;
				}
				node = node.getRightChild();
			} else {
				node = node.getLeftChild();
			}
		}

		return null;
	}

	@Override
	public boolean contains(T key) {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new RuntimeErrorException(null);
		}
		INode<T,V> node = root;
		while(!node.isNull()) {
			if(node.getKey().compareTo(key) == 0) {
				return true;
			}else if(node.getKey().compareTo(key) < 0) {
				node = node.getRightChild();
			}else {
				node = node.getLeftChild();
			}
		}
		return false;
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		node = getRoot();
		if (node.isNull()) {
			return false;
		}
		Stack<INode<T,V>> s = new Stack<INode<T,V>>();
		s.add(node);
		while (!s.isEmpty()) {
			node = s.pop();
			if (node.getValue().equals(value)) {
				return true;
			}
			if (!node.getRightChild().isNull()) {
				s.push(node.getRightChild());
			}
			if (!node.getLeftChild().isNull()) {
				s.push(node.getLeftChild());
			}
		}
		return false;
	}
   
	public Set<Map.Entry<T, V>> setMap(){
		INode<T,V> curr = root;
		if(curr.isNull()) {
			return null;
		}
		Stack<INode<T,V>> s = new Stack<INode<T,V>>(); 
		 Set<Map.Entry<T,V>> set = new LinkedHashSet<Map.Entry<T,V>>();
		
		   while (!curr.isNull() || !s.empty()) 
		    { 
            while (!curr.isNull()) 
		        { 
                    s.push(curr); 
		            curr = curr.getLeftChild(); 
		        } 
		        curr =  s.pop(); 
		        set.add(new AbstractMap.SimpleEntry<T, V>(curr.getKey(),curr.getValue()));
                curr = curr.getRightChild();
		    }
		  
		return set; 
		} 
	public Set<T> keySet(){
		Set<T> set = new TreeSet<>();
		
		INode<T,V> curr = root;
		if(curr.isNull()) {
			return null;
		}
		 Stack<INode<T,V>> s = new Stack<INode<T,V>>(); 
		   
		   while (!curr.isNull() || !s.empty()) 
		    { 
            while (!curr.isNull()) 
		        { 
                    s.push(curr); 
		            curr = curr.getLeftChild(); 
		        } 
		        curr =  s.pop(); 
		        set.add(curr.getKey());
                curr = curr.getRightChild();
		    }
		return set; 
		} 

	public Collection<V> values(){
		Collection<V> col = new LinkedList<V>();
		
		INode<T, V> curr = root;
		if(curr.isNull()) {
			return null;
		}
		 Stack<INode<T,V>> s = new Stack<INode<T,V>>(); 
		   
		   while (!curr.isNull() || !s.empty()) 
		    { 
            while (!curr.isNull()) 
		        { 
                    s.push(curr); 
		            curr = curr.getLeftChild(); 
		        } 
		        curr =  s.pop(); 
		        col.add( (V) curr.getValue());
                curr = curr.getRightChild();
		    }
		return col; 
		} 
	public ArrayList<Map.Entry<T,V>> heapMap(T toKey){
		ArrayList<Map.Entry<T,V>> set = new ArrayList<Map.Entry<T,V>>();
		
		INode<T,V> curr = root;
		if(curr.isNull()) {
			return null;
		}
		 Stack<INode<T,V>> s = new Stack<INode<T,V>>(); 
		   
		   while (!curr.isNull() || !s.empty()) 
		    { 
            while (!curr.isNull()) 
		        { 
               s.push(curr); 
               curr = curr.getLeftChild(); 
		        } 
		        curr =  s.pop(); 
		        if( curr.getKey().compareTo(toKey) > 0 ) {
		        	break;
		        }
		        Map.Entry<T,V> e = new AbstractMap.SimpleEntry<T, V>(curr.getKey(),curr.getValue());
		        set.add(e);
		        curr = curr.getRightChild();
		    }
		return set; 
		} 
		  
		


	@Override
	public void insert(T key, V value) {
		if (key == null || value == null) {
			throw new RuntimeErrorException(null);
		}
		INode<T,V> z = new Node<T,V>(null,null,null,key,value,true);
		INode<T,V> y = new Node<T,V>(null, null, null, null, null, false);
		INode<T,V> x = root;
		while (!x.isNull()) {
				y = x;
				if(y.getKey().compareTo(z.getKey())==0) {
					y.setValue(z.getValue());
					return;
				}
				else if (z.getKey().compareTo(x.getKey()) > 0) {
					x = x.getRightChild();
				} else {
					x = x.getLeftChild();	
				}
			}
		z.setParent(y);
		z.setLeftChild(new Node<T,V>(null, null, null, null, null, false));
		z.setRightChild(new Node<T,V>(null, null, null, null, null, false));
		if (y.isNull()) {
			root = z;
		} else if (z.getKey().compareTo(y.getKey()) > 0) {
			y.setRightChild(z);
		} else {
			y.setLeftChild(z);
		}
		if(z==root) {
			z.setColor(false);
			root.setColor(false);
		}else {
		z.setColor(true);
		}
		z.setLeftChild(new Node<T,V>(null, null, null, null, null, false));
		z.setRightChild(new Node<T,V>(null, null, null, null, null, false));
		if(z.getParent().getColor()) {
		checkColor(z);
		}
	size++;
	}
	private void checkColor(INode<T,V> z) {
		INode<T,V> uncle = new Node<T,V>(null,null,null,null,null,false);
		while(z.getParent().getColor()) {
			if(z.getParent().equals(z.getParent().getParent().getLeftChild())) {
				uncle = z.getParent().getParent().getRightChild();
				if(uncle.getColor()&&!uncle.isNull()) {
					uncle.setColor(false);
					z.getParent().setColor(false);
					z.getParent().getParent().setColor(true);
					z = z.getParent().getParent();
				}else if(z.equals(z.getParent().getRightChild())) {
					z = z.getParent();
					leftRotate(z);
				}else {
					z.getParent().setColor(false);
					z.getParent().getParent().setColor(true);
					rightRotate(z.getParent().getParent());
				}
			}else {
				uncle = z.getParent().getParent().getLeftChild();
				if(uncle.getColor()&&!uncle.isNull()) {
					uncle.setColor(false);
					z.getParent().setColor(false);
					z.getParent().getParent().setColor(true);
					z = z.getParent().getParent();
				}else if(z.equals(z.getParent().getLeftChild())) {
					z = z.getParent();
					rightRotate(z);
				}else {
					z.getParent().setColor(false);
					z.getParent().getParent().setColor(true);
					leftRotate(z.getParent().getParent());
				}
			}
		}
		root.setColor(false);
	}

	@Override
	public boolean delete(T key) {
		// TODO Auto-generated method stub
		
		if (key == null) {
					throw new RuntimeErrorException(null);
				}
				if(!contains(key)) {
					return false;
				}
				size--;
				INode<T,V> node = root;
				while (!node.isNull()) {
					if (node.getKey().compareTo((T) key) == 0) {
						deleteNode(node);
						break;
					} else if (key.compareTo(node.getKey()) > 0) {
						node = node.getRightChild();
					} else {
						node = node.getLeftChild();
					}
				}

				return true;
	}
	private void transPlant(INode<T,V> u, INode<T,V> v) {
		if(u.getParent().isNull()) {
			root = v;
		}else if(u.getParent().getLeftChild().equals(u)) {
			u.getParent().setLeftChild(v);
		}else {
			u.getParent().setRightChild(v);
		}
		v.setParent(u.getParent());
	}

	private void deleteNode(INode<T,V> z) {
		INode<T,V> x = new Node<T,V>(null,null,null,null,null,false);
		INode<T,V> y = z;
		boolean color  = y.getColor();
		if(z.getLeftChild().isNull()) {
			x = z.getRightChild();
			transPlant(z, z.getRightChild());
		}else if(z.getRightChild().isNull()) {
			x = z.getLeftChild();
			transPlant(z, z.getLeftChild());
		}else {
			 y = Minimum(z.getRightChild());
			 color = y.getColor();
			 x = y.getRightChild();
			 if(y.getParent().equals(z)) {
				 x.setParent(y);
			 }else {
				 transPlant(y, y.getRightChild());
				 y.setRightChild(z.getRightChild());
				 y.getRightChild().setParent(y);
			 }
			 transPlant(z, y);
			 y.setLeftChild(z.getLeftChild());
			 y.getLeftChild().setParent(y);
			 y.setColor(z.getColor());
		}
		if(!color) {
			fixDelete(x);
		}
	}
	private void fixDelete(INode<T,V> x) {
		INode<T,V> w = new Node<T,V>(null,null,null,null,null,false);
		while(!x.equals(root) && !x.getColor()) {
			if(x.getParent().getLeftChild().equals(x)) {
				w = x.getParent().getRightChild();
				if(w.getColor()) {
					w.setColor(false);
					x.getParent().setColor(true);
					leftRotate(x.getParent());
					w = x.getParent().getRightChild();
					}
					if(!w.getLeftChild().getColor() && !w.getRightChild().getColor()) {
						w.setColor(true);
						x = x.getParent();
						continue;
					}else if(!w.getRightChild().getColor() &&w.getLeftChild().getColor()){
						w.getLeftChild().setColor(false);
						w.setColor(true);
						rightRotate(w);
						w = x.getParent().getRightChild();
					}
					if(w.getRightChild().getColor()) {
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(false);
					w.getRightChild().setColor(false);
					leftRotate(x.getParent());
					x = root;
					}
				
			}else {
				w = x.getParent().getLeftChild();
				if(w.getColor()) {
					w.setColor(false);
					x.getParent().setColor(true);
					rightRotate(x.getParent());
					w = x.getParent().getLeftChild();
					}
					if(!w.getRightChild().getColor() && !w.getLeftChild().getColor()) {
						w.setColor(true);
						x = x.getParent();
						continue;
					}else if(!w.getLeftChild().getColor() && w.getRightChild().getColor()){
						w.getRightChild().setColor(false);
						w.setColor(true);
						leftRotate(w);
						w = x.getParent().getLeftChild();
					}
					if(w.getLeftChild().getColor()) {
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(false);
					w.getLeftChild().setColor(false);
					rightRotate(x.getParent());
					x = root;
					}
				
			}
		}
		x.setColor(false);
	}
	private void rightRotate(INode<T,V> x) {
		if(!x.isNull()) {
			INode<T,V> y = x.getLeftChild();
			x.setLeftChild(y.getRightChild());
			if(!y.getRightChild().isNull()) {
				y.getRightChild().setParent(x);
			}
			y.setParent(x.getParent());
			if(x.getParent().isNull()) {
				root = y;
			}else if(x.equals(x.getParent().getLeftChild())) {
				x.getParent().setLeftChild(y);
			}else {
				x.getParent().setRightChild(y);
			}
			y.setRightChild(x);
			x.setParent(y);
			}	
	}
	private void leftRotate(INode<T,V> x) {
		if(!x.isNull()) {
		INode<T,V> y = x.getRightChild();
		x.setRightChild(y.getLeftChild());
		if(!y.getLeftChild().isNull()) {
			y.getLeftChild().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent().isNull()) {
			root = y;
		}else if(x.equals(x.getParent().getLeftChild())) {
			x.getParent().setLeftChild(y);
		}else {
			x.getParent().setRightChild(y);
		}
		y.setLeftChild(x);
		x.setParent(y);
		}	
		}
		
      @SuppressWarnings("unused")
	private INode<T, V> getSuccessor(INode<T, V> node) {
		if(!node.getRightChild().isNull()) {
			return Minimum(node.getRightChild());
		}
		INode<T, V> parent = node.getParent();
		while(!parent.isNull() && node == parent.getRightChild()) {
			node = parent;
			parent = node.getParent();
		}
		return parent;
	}
	private INode<T, V> Minimum(INode<T, V> node) {
		while(!node.getLeftChild().isNull()) {
			node = node.getLeftChild();
		}
		return node;
	}

	public Map.Entry<T, V> Minimum() {
	
		INode<T, V> node = root;
        if(node.isNull()) {
        	return null;
        }
		while (!node.getLeftChild().isNull()) {
			node = node.getLeftChild();
		}
		Map.Entry<T, V> e = new AbstractMap.SimpleEntry<T, V>((T)node.getKey(),(V) node.getValue());
		return e;
	}
	public Map.Entry<T, V> Maximum() {
		Map.Entry<T, V> e ;
       INode<T, V> node = root;
       if(node.isNull()) {
    	   return null;
       }
		while (!node.getRightChild().isNull()) {
			node = node.getRightChild();
		}
		e= new AbstractMap.SimpleEntry<T, V>((T)node.getKey(),(V) node.getValue());
        
		return  e;
	}

	@SuppressWarnings("hiding")
	private class Node<T extends Comparable<T>, V> implements INode<T, V> {

		  private INode<T,V> parent ;
		   private INode<T,V> leftChild;
		   private INode<T,V> rightChild ;
		   private T key ;
		   private V value ;
		   private boolean color = false;
		   public Node (INode<T,V> parent,INode<T,V> left,INode<T,V> right,T key,V value,boolean color) {
			   this.parent = parent;
			   this.leftChild = left;
			   this.rightChild = right;
			   this.key = key;
			   this.value = value;
			   this.color = color;
		   }
		@Override
		public void setParent(INode<T,V> parent) {
			// TODO Auto-generated method stub
			this.parent = parent;
		}

		@Override
		public INode<T,V> getParent() {
			// TODO Auto-generated method stub
			return parent;
		}

		@Override
		public void setLeftChild(INode<T,V> leftChild) {
			// TODO Auto-generated method stub
			this.leftChild = leftChild;
		}

		@Override
		public INode<T,V> getLeftChild() {
			// TODO Auto-generated method stub
			return leftChild;
		}

		@Override
		public void setRightChild(INode<T,V> rightChild) {
			// TODO Auto-generated method stub
			this.rightChild = rightChild;
		}

		@Override
		public INode<T,V> getRightChild() {
			// TODO Auto-generated method stub
			return rightChild;
		}

		@Override
		public T getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public void setKey(T key) {
			// TODO Auto-generated method stub
			this.key = key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public void setValue(V value) {
			// TODO Auto-generated method stub
			this.value = value;
		}

		@Override
		public boolean getColor() {
			// TODO Auto-generated method stub
			return color;
		}

		@Override
		public void setColor(boolean color) {
			// TODO Auto-generated method stub
			this.color = color;
		}

		@Override
		public boolean isNull() {
			// TODO Auto-generated method stub
			if(key == null && value == null) {
				return true;
			}
			return false;
		}
	   }
	

}
