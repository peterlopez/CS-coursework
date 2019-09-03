// Programmer: Peter Ruszel
// Programmer's ID: 1611543
#include <iostream>
#include <string>
#include <vector>
using namespace std;

#include <cstdlib>

class Song {
    string name, artist;
public:
    Song(string, string);
    string getName();
    string getArtist();
};

void addSongToPlaylist(vector<Song>&, Song);
void deleteSongFromPlaylist(vector<Song>&, int);
void displayPlaylist(vector<Song>&);
void playlistMenu();

int main()
{
    cout << "Programmer: Peter Ruszel\n";
    cout << "Programmer's ID: 1611543\n";
    cout << "File: " << __FILE__ << endl;

    playlistMenu();
}

Song::Song(string n, string a)
{
    name = n;
    artist = a;
}
string Song::getName()
{
    return name;
}
string Song::getArtist()
{
    return artist;
}

void playlistMenu()
{
    vector<Song> playlist;

    // options for user
    const int CODE_ADD = 1;
    const int CODE_DELETE = 2;
    const int CODE_DISPLAY = 3;
    const int CODE_EXIT = 4;
    // selection by user
    int choice = 0;
    // used to display result of last operation
    string outputMsg;

    while (choice != CODE_EXIT) {
        system("CLEAR");
        if (!outputMsg.empty()) {
            cout << outputMsg << endl << endl;
            outputMsg = "";
        }
        cout << "Playlist menu" << endl;
        cout << "(" << CODE_ADD << ") Add a song to the playlist" << endl;
        cout << "(" << CODE_DELETE << ") Delete a song from the playlist" << endl;
        cout << "(" << CODE_DISPLAY << ") Display the playlist" << endl;
        cout << "(" << CODE_EXIT << ") Exit" << endl << endl;

        cout << "Make a selection : ";
        string buf;
        cin >> buf;
        choice = atoi(buf.c_str());
        cin.ignore(1000, 10);

        switch (choice) {
            case CODE_ADD: {
                system("CLEAR");
                cout << "Add song" << endl << endl;
                string newSongName, newSongArtist;
                cout << "Enter song name: ";
                getline(cin, newSongName);
                cout << "Enter song artist: ";
                getline(cin, newSongArtist);

                Song newSong(newSongName, newSongArtist);
                addSongToPlaylist(playlist, newSong);

                outputMsg = "New song added!";
                break;
            }
            case CODE_DELETE: {
                if (playlist.size() == 0) {
                    outputMsg = "No songs in playlist.";
                    break;
                }
                system("CLEAR");
                displayPlaylist(playlist);
                cout << endl << "-------------" << endl;

                int songNumber; // user-friendly index (starts at 1)
                do {
                    cout << "Enter song # to delete: ";
                    cin >> buf;
                    songNumber = atoi(buf.c_str());
                    cout << endl;
                } while(songNumber <= 0 || songNumber > playlist.size());

                deleteSongFromPlaylist(playlist, songNumber - 1);

                outputMsg = "Song #" + to_string(songNumber) + " deleted.";
                break;
            }
            case CODE_DISPLAY: {
                if (playlist.size() == 0) {
                    outputMsg = "No songs in playlist.";
                    break;
                }
                system("CLEAR");
                cout << "Playlist" << endl << endl;
                displayPlaylist(playlist);
                cout << endl << "Press [Enter] to continue." << endl;
                getline(cin, buf);
                break;
            }
            case CODE_EXIT:
                cout << "Exiting." << endl;
                break;
            default:
                cout << "Invalid option. Try again" << endl << endl;
        }
    }
}

void addSongToPlaylist(vector<Song>& playlist, Song newSong)
{
    playlist.push_back(newSong);
}

void deleteSongFromPlaylist(vector<Song>& playlist, int songIndex)
{
    playlist.erase(playlist.begin() + songIndex);
}

void displayPlaylist(vector<Song>& playlist)
{
    for (int i=0; i < playlist.size(); i++) {
        cout << "#" << (i+1) << ": " << playlist.at(i).getName() << " by " << playlist.at(i).getArtist() << endl;
    }
}
