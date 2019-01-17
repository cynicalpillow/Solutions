#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, R, xi1, xi2, yi1, yi2, vi;

struct flypath{
	int x;
	int y;
	int x2;
	int y2;
	int v;
};
flypath families[10005];
vector<int> result;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &N, &R);
	for(int i = 0; i < N; i++){
		scanf("%i%i%i%i%i", &xi1, &yi1, &xi2, &yi2, &vi);
		families[i] = {xi1, yi1, xi2, yi2, vi};
	}
	//30% points
	//faster/slower
	//moving opposite direction (regardless of speed)
	bool positive = (families[0].x2 > families[0].x);
	for(int i = 1; i < N; i++){
		if(abs(families[i].y - families[0].y) <= R){
			if(positive && (families[i].x2 > families[i].x)){ //moving same direction positive
				if(families[i].x < families[0].x && families[i].v <= families[0].v){ //if starting x is less, and speed is less
					if(pow(families[i].x-families[0].x, 2) + pow(families[i].y-families[0].y, 2) <= pow(R, 2))
						result.pb(i+1);
				} else if(families[i].x < families[0].x && families[i].v > families[0].v){ //if starting is less, and speed is more
					if(abs(families[i].y - families[0].y)<=R)
						result.pb(i+1);
				} else if(families[i].x > families[0].x && families[i].v < families[0].v){ //starting is more, speed is less
					if(abs(families[i].y - families[0].y)<=R)
						result.pb(i+1);
				} else if (families[i].x > families[0].x && families[i].v >= families[0].v){ //starting is more, speed is more
					if(pow(families[i].x-families[0].x, 2) + pow(families[i].y-families[0].y, 2) <= pow(R, 2))
						result.pb(i+1);
				} else if(families[i].x == families[0].x){ //starting is equal
					if(abs(families[i].y - families[0].y)<=R)
						result.pb(i+1);
				}
			} else if(!positive && (families[i].x2 < families[i].x)){ //both negative direction
				if(families[i].x > families[0].x && families[i].v <= families[0].v){ //if starting x is less, and speed is less
					if(pow(families[i].x-families[0].x, 2) + pow(families[i].y-families[0].y, 2) <= pow(R, 2))
						result.pb(i+1);
				} else if(families[i].x > families[0].x && families[i].v > families[0].v){ //if starting is less, and speed is more
					if(abs(families[i].y - families[0].y)<=R)
						result.pb(i+1);
				} else if(families[i].x < families[0].x && families[i].v < families[0].v){ //starting is more, speed is less
					if(abs(families[i].y - families[0].y)<=R)
						result.pb(i+1);
				} else if (families[i].x < families[0].x && families[i].v >= families[0].v){ //starting is more, speed is more
					if(pow(families[i].x-families[0].x, 2) + pow(families[i].y-families[0].y, 2) <= pow(R, 2))
						result.pb(i+1);
				} else if(families[i].x == families[0].x){ //starting is equal
					if(abs(families[i].y - families[0].y)<=R)
						result.pb(i+1);
				}
			} else { //opposites
				if(positive && !(families[i].x2 > families[i].x)){ //one moving positive, other moving negative
					if(families[i].x < families[0].x){//starting is less
						if(pow(families[i].x-families[0].x, 2) + pow(families[i].y-families[0].y, 2) <= pow(R, 2))
							result.pb(i+1);
					} else if(families[i].x > families[0].x){//more
						if(abs(families[i].y - families[0].y)<=R)
							result.pb(i+1);
					} else if(families[i].x == families[0].x){//equal
						if(abs(families[i].y - families[0].y)<=R)
							result.pb(i+1);
					}
				} else { //moving negative, other moving positive
					if(families[i].x > families[0].x){
						if(pow(families[i].x-families[0].x, 2) + pow(families[i].y-families[0].y, 2) <= pow(R, 2))
							result.pb(i+1);
					} else if(families[i].x < families[0].x){
						if(abs(families[i].y - families[0].y)<=R)
							result.pb(i+1);
					} else if(families[i].x == families[0].x){
						if(abs(families[i].y - families[0].y)<=R)
							result.pb(i+1);
					}
				}
			}
		}
	}
	for(int i = 0; i < result.size(); i++)
		cout << result[i] << "\n";
	return 0;
}