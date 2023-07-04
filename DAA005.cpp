#include <iostream>
#include <cmath>

using namespace std;

int main(){
    int n;
    cin >> n;
    int lista[n];
    lista[0] = 0;
    int sum;
    for(int i = 1;i<n+1;i++){
        cin >> sum;
        lista[i] = lista[i-1] + sum;
    }
    int f;
    cin >> f;
    int a;
    int b;
    for(int i = 0;i<f;i++){
        cin >> a;
        cin >> b;
        cout << lista[b] -lista[a-1] << endl;
    }
    return 0;
}