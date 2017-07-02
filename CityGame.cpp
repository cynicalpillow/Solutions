#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int K, M, N;
int original[1005][1005];
int mem[1005][1005];

int maxHist(int r){
    stack<int> result;
    int top_val;
    int max_area = 0;
    int area = 0; 
    int i = 0;
    while (i < N){
        if (result.empty() || mem[r][result.top()] <= mem[r][i])
            result.push(i++);
        else{
            top_val = mem[r][result.top()];
            result.pop();
            area = top_val * i;
            if (!result.empty()) area = top_val * (i - result.top() - 1 );
            max_area = max(area, max_area);
        }
    }
    while (!result.empty()){
        top_val = mem[r][result.top()];
        result.pop();
        area = top_val * i;
        if (!result.empty())area = top_val * (i - result.top() - 1 );
        max_area = max(area, max_area);
    }
    return max_area;
}

void solve(){
	int result = 0;
	for(int i = 0; i < M; i++){
		for(int j = 0; j < N; j++){
			mem[i][j] = 0;
			if(i > 0){
				if(original[i][j] == 0)mem[i][j] += mem[i-1][j] + 1;
				else mem[i][j] = 0; 
			} else {
				if(original[i][j] == 0)mem[i][j] = 1;
				else mem[i][j] = 0; 
			}
		}
		result = max(result, maxHist(i));
	}
	cout << result*3 << endl;
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i", &K);
	for(int i = 0; i < K; i++){
		scanf("%i%i", &M, &N);
		for(int j = 0; j < M; j++){
			for(int z = 0; z < N; z++){
				string val;
				cin >> val;
				if(val == "R") original[j][z] = -1;
				else original[j][z] = 0;
			}
		}
		solve();
	}
	return 0;
}