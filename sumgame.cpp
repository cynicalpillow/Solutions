#include <bits/stdc++.h>
using namespace std;

int N;
int A[100005];
int B[100005];

int main(){
	cin >> N;
	for(int i = 0; i < N; i++){
		cin >> A[i];
	}
	for(int i = 0; i < N; i++){
		cin >> B[i];
	}
	int Asum = 0; 
	int Bsum = 0;
	int largestDay = 0;
	for(int i = 0; i < N; i++){
		Asum += A[i];
		Bsum += B[i];
		if(Asum == Bsum)largestDay = i+1;
	}
	cout << largestDay;
	return 0;
}