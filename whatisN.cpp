#include <bits/stdc++.h>
using namespace std;

int INFINITE = 0x3f3f3f3f;
int N;
bool visited[11];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
    freopen("input.txt", "r", stdin);
	cin >> N;
	int count = 0;
	memset(visited, false, sizeof(visited));
	for(int i = N; i >= 0; i--){
		if(i <= 5 && N-i <= 5 && !visited[i] && !visited[N-i]){
			visited[i] = true;
			visited[N-i] = true;
			count++;
		}
	}
	cout << count;
	return 0;
}