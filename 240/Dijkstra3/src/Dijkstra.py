class Dijkstra():
    Grap.Edge = [
        Graph.Edge("1", "2", 7),
        Graph.Edge("1", "3", 9),
        Graph.Edge("1", "6", 14),
        Graph.Edge("2", "3", 10),
        Graph.Edge("2", "4", 15),
        Graph.Edge("3", "4", 11),
        Graph.Edge("3", "6", 2),
        Graph.Edge("4", "5", 6),
        Graph.Edge("5", "6", 9),
    ]
    START = "1"
    END = "6"

    def main(self):
        g = Graph()
        g.djikstra(START)
        g.printPath(END)