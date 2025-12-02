#include <iostream>
#include <sstream>
#include <string>

int main() {
    long long runsScored = 0;
    long long runsAllowed = 0;
    int games = 0;

    std::string line;
    // Read from stdin until EOF
    while (std::getline(std::cin, line)) {
        if (line.empty()) {
            continue;
        }

        std::istringstream iss(line);
        int teamScore = 0;
        int oppScore = 0;

        if (!(iss >> teamScore >> oppScore)) {
            // Bad line, skip
            continue;
        }

        runsScored += teamScore;
        runsAllowed += oppScore;
        games++;
    }

    long long rd = runsScored - runsAllowed;

    double expectedWinPct = 0.0;
    if (runsScored > 0 || runsAllowed > 0) {
        double rs2 = static_cast<double>(runsScored) * runsScored;
        double ra2 = static_cast<double>(runsAllowed) * runsAllowed;
        expectedWinPct = rs2 / (rs2 + ra2);
    }

    // Print compact JSON to stdout
    std::cout.precision(6);
    std::cout << std::fixed;
    std::cout
        << "{"
        << "\"games\":" << games << ","
        << "\"runsScored\":" << runsScored << ","
        << "\"runsAllowed\":" << runsAllowed << ","
        << "\"runDifferential\":" << rd << ","
        << "\"expectedWinPct\":" << expectedWinPct
        << "}" << std::endl;

    return 0;
}