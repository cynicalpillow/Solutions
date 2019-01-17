#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N;
int sizes[2005];
int sums[4005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		int x;
		cin >> x;
		sizes[x]++;
	}
	for(int i = 1; i <= 2000; i++){
		if(sizes[i]){
			for(int j = i; j <= 2000; j++){
				if(sizes[j]){
					if(i == j)sums[i+j] += (sizes[i]/2);
					else {
						sums[i+j] += min(sizes[i], sizes[j]);
					}
				}
			}
		}
	}
	int mSum = 0;
	int mSize = 0;
	for(int i = 1; i <= 4000; i++){
		if(mSum < sums[i]){
			mSum = sums[i];
			mSize = 1;
		} else if(mSum == sums[i]){
			mSize++;
		}
	}
	cout << mSum << " " << mSize;
	return 0;
}