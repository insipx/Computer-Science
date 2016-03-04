class BinarySearchTree (object):
	def __init__(self):
		self.head = Node()
		
	def insert(self, node, value):
		if (node.value == None):
			node.value = value
		elif (node.value > value):
			if node.left == None:
				node.left = Node()
				node.left.parent = node
			self.insert(node.left, value)
		elif (node.value < value):
			if node.right == None:
				node.right = Node()
				node.right.parent = node
			self.insert(node.right, value)
			
	def delete(self, node, value):
		if (node == None):
			return 
		elif (node.parent == None and node.right == None and node.left == None):
			node = None
		elif (node.right == None and node.left == None):
			if (node.value > value):
				node.parent.left = None
			elif (node.value < value):
				node.parent.right = None
		elif (node.value > node.parent.value and node.right != None):
			node.parent.right = node.right
			node.right.parent = node.parent
		elif (node.value < node.parent.value and node.left != None):
			node.parent.left = node.left
			node.left.parent = node.parent
		else:
			print('Nope.')
	
	def dumptree(self, node):
		try:
			self.dumptree(node.left)
		except:
			pass
		print(node.value)
		try:
			self.dumptree(node.right)
		except:
			pass
		
class Node (object):
	def __init__(self):
		self.right = None
		self.left = None
		self.parent = None
		self.value = None
		
		
class BinarySearchTreeDriver (object):
	def main(self):
		bst = BinarySearchTree()
		bst.insert(bst.head, 5)
		bst.insert(bst.head, 3)
		bst.insert(bst.head, 1)
		bst.insert(bst.head, 2)
		bst.insert(bst.head, 4)
		bst.dumptree(bst.head)
		bst.delete(bst.head, 3)
		bst.dumptree(bst.head)
		
if __name__ == "__main__":
	b = BinarySearchTreeDriver()
	b.main()