//COSC 2P95
//Lab 2 Exercise
//Micah Rose-Mighty
//6498935
//mr18kq@brocku.ca
#include <iostream>
#include<string>


int main(){
    
    int num[7] = {6,4,9,8,9,3,5}; 
    int cNum;
    int x;
    int choice1;
    int choice2;
    int b;
    int m = 1000000;
    

    for(int i=0; i<7; i++){
        cNum = num[i];
       
        if (cNum%2 == 0){
            std::cout<<cNum/2;
        }

        else if(cNum*2>=10) {
            std::cout<<cNum*2-10;
        }
        else{
            std::cout<<cNum*2;
        }
    }
    std::cout<<"\n";

    std::cout<<"How many monkeys do I have? ";std::cin>>x;

    float wholeNum = (float)x/(float)m;

    b = (x>m) ? wholeNum:x;

    int remainder = (x>m) ? x-b*m:nullptr;

    std::cout<<"I have " << b << "." << remainder << (x == 1 ? " single monkey" : x <= m ? " monkeys" : x > m ? " million monkeys" : "\n")<<".\n";


    std::cout<<"Do you have room for tiramisu? (1:yes, 0:no)"; std::cin>>choice1;
    std::cout<<"Do you actually like tiramisu? (1:yes, 0:no)"; std::cin>>choice2;

    if(choice1){
        if(choice2){
            std::cout<<"Ready, willing, and able to enjoy tiramisu!\n"; 
        }
    }
    else {
        std::cout<<"It doesn't matter if I like it or not, I don't have room for dessert!\n";
    }
   

}


