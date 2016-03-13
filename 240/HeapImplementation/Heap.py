# coding: utf-8
# Heap test implementation
# Python test script
# Sean Batzel

class Heap (object): # class Heap implements HeapInterface()
	def __init__(self): # public Heap()
		self.heap = [] # int[] heap = new int[20];
		
	def insert(self, num): # public void insert(int num)
		self.heap.append(num) # Do I need to write an append operation to extend the array?
		self.sort_heap()
		
	def extract_max(self):
		self.heap.remove(self.heap[0]) # I could easily implement this one too. 
		self.sort_heap()
		
	def sort_heap(self):
		for x in range(len(self.heap)):
			for x in range(len(self.heap)):
				if self.heap[x] > self.heap[self.get_parent(x)]:
					#self.swap(x, self.get_parent(x))
					tmp = self.heap[x]
					self.heap[x] = self.heap[self.get_parent(x)]
					self.heap[self.get_parent(x)] = tmp
		
	def get_right(self, x):
		return ((2*x) + 1)
		
	def get_left(self, x):
		return (2*x)
		
	def get_parent(self, x):
		return (x/2)
		
	def dump_heap(self):
		print(self.heap)
				
	''' This is extraordinarily not right.
	def swap(self, x, y):
		tmp = self.heap[x]
		self.heap[y] = self.heap[x]
		self.heap[y] = tmp
	'''
				
class HeapDriver (object):
	def main(self):
		heap = Heap()
		heap.insert(5)
		heap.insert(6)
		heap.insert(7)
		heap.insert(3)
		heap.insert(9)
		heap.insert(18)
		heap.dump_heap()
		heap.extract_max()
		heap.dump_heap()
		heap.extract_max()
		heap.dump_heap()
		
hd = HeapDriver()
hd.main()