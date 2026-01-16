#!/usr/bin/env bash
set -e

KEY_DIR="./secrets"
KEY_FILE="$KEY_DIR/jwt-private.pem"

mkdir -p "$KEY_DIR"

if [ -f "$KEY_FILE" ]; then
  echo "JWT private key already exists at $KEY_FILE"
  exit 0
fi

echo "Generating JWT private key..."

openssl genpkey \
  -algorithm RSA \
  -pkeyopt rsa_keygen_bits:2048 \
  -out "$KEY_FILE"

echo "JWT private key generated at $KEY_FILE"
