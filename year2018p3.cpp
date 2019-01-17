#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

long long INFINITE = 446744073709551615;
int N, M, K;
long long vals[100005];
long long diff[100005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> M >> K;
	long long minValue = INFINITE;
	long long maxValue = 0;
	for(int i = 0; i < N; i++){
		cin >> vals[i];
		minValue = min(minValue, vals[i]);
		maxValue = max(maxValue, vals[i]);
	}
	maxValue+=K;
	long long low = minValue;
	long long high = maxValue;
	long long mid = 0;
	while(low < high){
		mid = low+(high-low+1)/2;
		long long add = 0;
		long long count = 0;
		bool flag = false;
		memset(diff, 0, sizeof(diff));
		for(int i = 0; i < N; i++){
			if(count < K && i + M - 1 < N && vals[i] + add < mid){
				diff[i] = mid - (vals[i]+add);
				diff[i+M-1] = -diff[i];
				count++;
			} else if(vals[i] + add < mid && count >= K){
				high = mid-1;
				flag = true;
				break;
			} else if(vals[i] + add < mid && i + M - 1 >= N){
				diff[i] = mid - (vals[i]+add);
			}
			add += diff[i];
		}
		cout << count << " " << mid << " " << low << " " << high << endl;
		if(count < K && !flag)
			low = mid;
	}
	cout << low << endl;
	return 0;
}