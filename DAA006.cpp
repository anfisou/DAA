#include <iostream>
#include <cmath>
#include <math.h>

using namespace std;

bool isinside(double x,double y, int cx,int cy, int cr){
    return sqrt(pow(x-cx,2) + pow(y-cy,2)) < cr;
}

double intersecao(double x, double y, double l, int cx, int cy, int cr){
    if (sqrt(pow(x+l/2-cx,2) + pow(y+l/2-cy,2))> cr + l / sqrt(2)){return 0;}
    if (isinside(x,y,cx,cy,cr) && isinside(x+l,y,cx,cy,cr) && isinside(x,y+l,cx,cy,cr) && isinside(x+l,y+l,cx,cy,cr)){return pow(l,2);}
    if (cx-cr>x && cx+cr<x+l && cy-cr>y && cy+cr<y+l){return M_PI * pow(cr,2);}
    double area = 0;
    if (l>=0.001){
        area += intersecao(x,y,l/2,cx,cy,cr);
        area += intersecao(x+l/2,y,l/2,cx,cy,cr);
        area += intersecao(x,y+l/2,l/2,cx,cy,cr);
        area += intersecao(x+l/2,y+l/2,l/2,cx,cy,cr);}
    return area;
}


int main(){
    int n;
    cin >> n;
    int qx,qy,ql,cx,cy,cr;
    for(int i = 0;i<n;i++){
        cin >> qx;
        cin >> qy;
        cin >> ql;
        cin >> cx;
        cin >> cy;
        cin >> cr;
        cout << intersecao(qx,qy,ql,cx,cy,cr) << endl;
    }
    return 0;
}