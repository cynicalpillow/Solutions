#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
vector<long> sums;
long N, K;
int T;

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i", &T);
	long max = 1000000001;
	long curr = 1;
	long runningTotal = 0;
	for(int i = 1; i < 1000000001; i+=curr){
		if(runningTotal + i > max)break;
		if(i == 1)sums.push_back(1);
		else sums.push_back(sums.back() + i);
		curr++;
		runningTotal += i;
	}
	for(int i = 0; i < T; i++){
		scanf("%ld%ld", &N, &K);
		long result = 0;
		for(int j = 0; j < sums.size(); j++){
			if(sums[j] > (N-K))break;
			result = sums[j];
		}
		cout << result << "\n";
	}
	return 0;
}