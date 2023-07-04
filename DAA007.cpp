#include <iostream>
#include <cmath>

using namespace std;

int main(){
    int n;
    cin >> n;
    int val;
    int bestprev;
    int bestactual;
    cin >> bestprev;
    int max = bestprev;
    for(int i = 1;i<n;i++){
        cin >> val;
        if (bestprev<0) {bestactual = val;}
        else{bestactual = bestprev+val;}
        if (bestactual>max){max = bestactual;}
        bestprev = bestactual;
    }
    cout << max << endl;
}