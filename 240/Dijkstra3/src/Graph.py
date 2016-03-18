class Graph():
    class Edge():
        def __init__(self, v1, v2, dist):
            self.v1 = v1
            self.v2 = v2
            self.dist = dist

    class Vertex():
        def __init__(self):
            self.name = name;

        def printPath(self):
            if (self == self.previous):
                print(self.name)
            elif (this.previous == None):
                print(self.name + "Unreachable")
            else:
                self.previous.printPath()
                print(" -> " + self.name + "(" + self.dist + ")")

        def compareTo(self, other):
            return