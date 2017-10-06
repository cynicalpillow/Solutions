#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, M;
int cnt = 0;
string input;
struct car {
	int id;
	int x;
	int y;
	int dir;
	int up;
	int down;
	int left;
	int right;
};
bool visited[5000000];
int path[5000000];

bool compare_x(car c1, car c2){return c1.x < c2.x;}
bool compare_y(car c1, car c2){return c1.y < c2.y;}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	memset(visited, false, sizeof(visited));
	memset(path, -1, sizeof(path));
	cin >> N >> M;
	for(int i = 0; i < N; i++){
		cin >> input;
		for(int j = 0; j < M; j++){
			if(input[j] != '.'){
				car z; z.x = j; z.y = i; z.id = cnt;
				if(input[j] == 'N')z.dir = 0;
				else if(input[j] == 'S')z.dir = 1;
				else if(input[j] == 'W')z.dir = 2;
				else if(input[j] == 'E')z.dir = 3;
				c_x_sorted.pb(z);
				c_y_sorted.pb(z);
				cnt++;
			}
		}
	}
	sort(c_x_sorted.begin(), c_x_sorted.end(), compare_x);
	sort(c_y_sorted.begin(), c_y_sorted.end(), compare_y);

	bool found = false;

	return 0;
}