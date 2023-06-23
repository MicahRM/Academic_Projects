//cosc 2p95 lab 8 exercise
//micah rose-mighty
//6498935
//mr18kq@brocku.ca
//2022/03/16

//Includes
#include <iostream>
#include <string>
#include <iomanip>
#include <fstream>


//less text
using namespace std;


/**
 * This method recursively calls a Topological Sort with all prior calculated variables
 * 
 *
void DFT(int sV, string *vArr, bool adjM[][20], bool *vV, int n){
    
    
    //printing vertex
    cout << vArr[sV] << " ";


    //setting vertex to true
    vV[sV] = true;

    for(int i = 0; i<n; i++){
        if(adjM[sV][i] && !vV[i]){
            DFT(i,vArr,adjM,vV,n);
        }
    }

} //DFT*/

/*void TPS(int sV, string *vArr, bool adjM[][20], bool *vV, int n){


    

    
    
    //printing vertex
    cout << vArr[sV] << " ";


    //setting vertex to true
    vV[sV] = true;

    for(int i = 0; i<n; i++){
        if(adjM[sV][i] && !vV[i]){
            DFT(i,vArr,adjM,vV,n);
        }
    }

} //TPS*/






int main(){
    string fileName;
    cout << "Graph filename: ";
    cin >> fileName;
    cout << "Using " << fileName << endl;
    fstream fin (fileName.c_str(), ios::in);

    int numOfVertices;
    fin >> numOfVertices;
    if(fin.fail()){
        fin.clear(ios::eofbit&fin.rdstate()|ios::goodbit);
        fin.ignore(1);
    }
    
    //get vertices from file

    string vertices;
    string vertexArr[numOfVertices];
    getline(fin,vertices);
    for (int i =0; i<numOfVertices; i++){
        if(i != numOfVertices-1){
            getline(fin, vertices, '\t');

        }
        
        else{
            getline(fin, vertices, '\n');
        }

        if(fin.fail()){
            fin.clear(ios::eofbit&fin.rdstate()|ios::goodbit);
            fin.ignore(1);
        }
        else vertexArr[i] = vertices;
        


    }

    int numOfEdges;
    fin >> numOfEdges;
    if(fin.fail()){
        fin.clear(ios::eofbit&fin.rdstate()|ios::goodbit);
        fin.ignore(1);
    }
    

    bool adjMatrix[20][20];
    for(int i = 0; i<20; i++){
        for(int j =0; j<20; j++){
            adjMatrix[i][j] = false;



        }
    }
    //to keep track of vertex/edge directions
    int x;
    int y;

    while(fin){
        fin >> x; //set x
        if(fin.fail()){
            fin.clear(ios::eofbit&fin.rdstate()|ios::goodbit);
            fin.ignore(1);
        }
        

        fin >> y; //set y
        if(fin.fail()){
            fin.clear(ios::eofbit&fin.rdstate()|ios::goodbit);
            fin.ignore(1);
        }
        adjMatrix[x][y] = true;
    }



    cout << "Loaded Graph" << endl;


    //printing vertices

    cout << "\n Vertices: " << endl;
    for(int i=0; i<numOfVertices; i++){
        cout << '[' << i << ": " << vertexArr[i] << "]";
        if(i != numOfVertices -1 ){
            cout << ",";
        }
        else{
            cout << "\n";
        }
    }


    //printing edges

    cout << "\nEdges: " << endl;
    for(int i = 0; i<numOfVertices; i++){
        cout << vertexArr[i] << " -> ";
        bool comma = true;
        for(int j = 0; j<numOfVertices; j++){
            if(adjMatrix[i][j]){
                if(!comma){
                    cout << " , ";
                }
                cout << vertexArr[j];
                comma = false;
            }
        }
        cout << " \n";
    }

    //main loop
    while(true){
        //choose starting vertex
        int vertexChoice;
        //cout << "Starting vertex number for DFT: ";
        //cout << "Starting vertex for TPS (Type 0 to start at the first vertex of the graph): ";
        cin >> vertexChoice;
        


        // if vertex chosen is not there, quit
        if(vertexChoice<0 || vertexChoice >= numOfVertices){
            break;
        }

        //initialize visited array fpr each vertex
        bool visited[numOfVertices];
        for(int i = 0; i<numOfVertices; i++){
            visited[i] = false;


    
        }
        //run dft
        //DFT(vertexChoice, vertexArr, adjMatrix,visited,numOfVertices);
        //TPS(vertexChoice, vertexArr, adjMatrix,visited,numOfVertices);
        

        //new line
        cout <<"\n";



    }

    //close the file
    fin.close();

    return 0;




} //main
