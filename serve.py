"""Máy chủ tĩnh — Hôm nay chơi gì? Web pilot (port mặc định 5179)."""

import socket
import sys
from http.server import SimpleHTTPRequestHandler, ThreadingHTTPServer


class Handler(SimpleHTTPRequestHandler):
    extensions_map = {
        **SimpleHTTPRequestHandler.extensions_map,
        ".mjs": "text/javascript",
        ".json": "application/json",
    }


def lan_addresses(port):
    found = []
    try:
        for info in socket.getaddrinfo(socket.gethostname(), None, socket.AF_INET):
            address = info[4][0]
            if not address.startswith("127.") and address not in found:
                found.append(address)
    except OSError:
        pass
    return [f"http://{address}:{port}/" for address in found]


if __name__ == "__main__":
    host = sys.argv[1] if len(sys.argv) > 1 else "127.0.0.1"
    port = int(sys.argv[2]) if len(sys.argv) > 2 else 5179
    print(f"Gau Con Web tai http://{host}:{port}/", flush=True)
    for url in lan_addresses(port):
        print(f"  LAN: {url}", flush=True)
    ThreadingHTTPServer((host, port), Handler).serve_forever()
