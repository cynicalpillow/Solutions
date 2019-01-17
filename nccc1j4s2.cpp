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
int arr[37][37];
bool visited[38];

bool is_latin(){
	for(int i = 0; i < N; i++){
		memset(visited, false, sizeof(visited));
		for(int j = 0; j < N; j++){
			if(visited[arr[i][j]])return false;
			visited[arr[i][j]] = true;
		}
	}
	for(int j = 0; j < N; j++){
		memset(visited, false, sizeof(visited));
		for(int i = 0; i < N; i++){
			if(visited[arr[i][j]])return false;
			visited[arr[i][j]] = true;
		}
	}
	return true;
}

bool is_reduced(){
	for(int i = 0; i < N; i++){
		if(i > 0){
			if(arr[i-1][0] > arr[i][0])return false;
		}
	}
	for(int j = 0; j < N; j++){
		if(j > 0){
			if(arr[0][j-1] > arr[0][j])return false;
		}
	}
	return true;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		string x;
		cin >> x;
		for(int j = 0; j < N; j++){
			if(isdigit(x[j]))arr[i][j] = x[j]-'0';
			else arr[i][j] = 10+x[j]-'A';
		}
	}
	if(is_latin()){
		if(is_reduced()){
			cout << "Reduced";
		} else {
			cout << "Latin";
		}
	} else {
		cout << "No";
	}
	return 0;
}