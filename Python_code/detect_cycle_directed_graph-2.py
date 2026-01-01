# File Name: detect_cycle_directed_graph.py

def hasCycle(graph):
    visited = set()
    rec_stack = set()

    def dfs(node):
        visited.add(node)
        rec_stack.add(node)

        for neighbor in graph.get(node, []):
            if neighbor not in visited:
                if dfs(neighbor):
                    return True
            elif neighbor in rec_stack:
                return True

        rec_stack.remove(node)
        return False

    for node in graph:
        if node not in visited:
            if dfs(node):
                return True

    return False


if __name__ == "__main__":
    graph = {
        0: [1],
        1: [2],
        2: [0],
        3: [4],
        4: []
    }

    print("Cycle Detected:", hasCycle(graph))
