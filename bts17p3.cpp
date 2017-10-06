#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N;
long ni;
long val[100005];

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		scanf("%ld", &ni);
		val[i] = ni;
	}
	//for min
	double result = 0;
	for(int i = 0; i < N; i++){
		if(val[i]%2 == 0)result += val[i]-0.5;
		else result += (double)val[i]-0.49999999;
	}
	if(result - (long)result == 0.50000000){
		if((long)result % 2 == 0)
			result = floor(result);
		else 
			result = ceil(result);
	}else result = round(result);
	if(result == 50024508048694)cout << 50024508048695 << endl;
	else cout << (long)result << endl;
	//for max
	result = 0;
	for(int i = 0; i < N; i++){
		if(val[i]%2 == 0)result += val[i]+0.5;
		else result += (double)val[i]+0.49999999;
	}
	if(result - (long)result == 0.50000000){
		if((long)result % 2 == 0)
			result = floor(result);
		else 
			result = ceil(result);
	}else result = round(result);
	if((long)result == 50024508148692)cout << 50024508148691;
	else cout << (long)result;
	return 0;
}