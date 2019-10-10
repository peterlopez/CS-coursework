// Programmer: Peter Ruszel
// Programmer's ID: 1611543
#include <iostream>
#include <vector>
#include <string>

using namespace std;

// Infinite distance
// all vertices' distance will be initalized to this
// according to Dijkstra's algorithm
const int INF = 9999999;

// Define 5x5 adjacency matrix graph
const vector<vector<int>> graph{
    // 0   1   2   3   4   5
    {  0,  3,  0,  0,  0,  0 }, //0
    {  3,  0,  1,  5, 17,  0 }, //1
    {  0,  1,  0,  0,  0,  4 }, //2
    {  0,  5,  0,  0,  2,  0 }, //3
    {  0, 17,  0,  2,  0,  8 }, //4
    {  0,  0,  4,  0,  8,  0 }, //5
};
//       Diagram
//
//      3       1
// {0} --- {1} --- {2}
//        / |       |
//     5 /  |17     |4
//      /   |       |
// {3} --- {4} --- {5}
//      2       8
//

class Vertex {
    // ID of the previous vertex
    // along the shortest path from the start vertex
    int predecessor;

    // distance from the start vertex
    int distance;
public:
    // Initalize new vertex's distance and predecessor
    Vertex() {
        distance = INF;
        predecessor = 0;
    }
    int getPre() {
        return predecessor;
    }
    void setPre(int newPre) {
        predecessor = newPre;
    }
    int getDist() {
        return distance;
    }
    void setDist(int newDist) {
        distance = newDist;
    }
};

void DijkstraShortestPath(vector<vector<int>>, int);

int findClosestNonVisited(vector<Vertex>, int, bool[]);

int main()
{
    cout << "Programmer: Peter Ruszel\n";
    cout << "Programmer's ID: 1611543\n";
    cout << "File: " << __FILE__ << endl << endl;

    // For each vertex, find the shortest paths
    // from it to all other vertices in the graph
    const int numV = graph.size();
    for (int i=0; i < numV; i++) {
        DijkstraShortestPath(graph, i);
    }

    return 0;
}

void DijkstraShortestPath(vector<vector<int>> graph, int start)
{
    const int numV = graph.size();

    // setup list of verticies
    vector<Vertex> v;
    bool visited[numV];
    for (int i=0; i < numV; i++) {
        Vertex newV;

        v.push_back(newV);
        visited[i] = false;
    }

    // Initalize start vertex
    Vertex& startV = v.at(start);
    startV.setDist(0);

    int currentV = start;
    for (int i=0; i < numV; i++)
    {
        // visit vertex with minimum distance from currentV
        currentV = findClosestNonVisited(v, currentV, visited);
        visited[currentV] = true;

        // loop through adjacent vertexes
        for (int j=0; j < numV; j++)
        {
            // skip if not adjacent
            if (graph[currentV][j] == 0) {
                continue;
            }
            int adjacentV = j;

            // distance between currentV and adjacentV
            int dist1 = graph[currentV][j];
            // distance from start to adjacentV
            int dist2 = v.at(currentV).getDist() + dist1;

            if (v.at(adjacentV).getDist() == INF || dist2 < v.at(adjacentV).getDist()) {
                v.at(adjacentV).setDist(dist2);
                v.at(adjacentV).setPre(currentV);
            }
        }
    }

    // output shortest distance and shortest path
    // between vertices
    for (int p=0; p < numV; p++) {
        if (p == start) {
            continue;
        }
        cout << "shortest distance from Vertex " << start << " to Vertex " << p;
        cout << " is: " << v.at(p).getDist() << " via route.." << endl;
        
        // build route backwards then reverse it
        int predecessor = p;
        string route = "";
        while (predecessor != start) {
            route += to_string(predecessor) + " >> "; // put the '>>' backwards for reverse()
            predecessor = v.at(predecessor).getPre();
        }
        route += to_string(start);
        reverse(route.begin(), route.end());

        cout << "\t" << route << endl;
    }
}

/**
 * Find closest non-visited vertex
 *
 * @param v | vector containing all vertices
 * @param currentV | index of current vertex
 * @param visited | array denoting already visited vertices
 * @return index of closest vertex to currentV
 */
int findClosestNonVisited(vector<Vertex> v, int currentV, bool visited[])
{
    int closest = -1;
    for (int i=0; i < v.size(); i++) {
        // ignore already visited vertices
        if (visited[i] == true) {
            continue;
        }
        // assign first unvisited vertex as closest
        else if (closest == -1) {
            closest = i;
        }
        // compare current vertex distance with closest vertex distance
        else if (v.at(i).getDist() < v.at(closest).getDist()) {
            closest = i;
        }
    }

    return closest;
}
