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
vector<long long> arr;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		long long ai;
		cin >> ai;
		arr.pb(ai);
	}
	sort(arr.begin(), arr.end());
	long long count = 0;
	long long ans = 0;
	for(int i = arr.size()-1; i >= 0; i--){
		while(arr[i] > 0){
			int found = 0;
			for(int j = i-1; j >= 0 && arr[i] > 0; j--){
				if(arr[j] > 0){
					count++;
					arr[i]--;
					arr[j]--;
					if(arr[j] > 0)found++;
				}
			}
			if(found <= 0)break;
		}
	}
	cout << count;
	return 0;
}