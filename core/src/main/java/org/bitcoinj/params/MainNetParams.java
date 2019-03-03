/*
 * Copyright 2013 Google Inc.
 * Copyright 2015 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.params;

import org.bitcoinj.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

import static com.google.common.base.Preconditions.*;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends AbstractBitcoinNetParams {
    private static final Logger log = LoggerFactory.getLogger(MainNetParams.class);

    public static final int MAINNET_MAJORITY_WINDOW = 1000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 950;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 750;

    public static final int MAINNET_MAJORITY_DIP0001_WINDOW = 4032;
    public static final int MAINNET_MAJORITY_DIP0001_THRESHOLD = 3226;

    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = CoinDefinition.proofOfWorkLimit;
        dumpedPrivateKeyHeader = 204;
        addressHeader = CoinDefinition.AddressHeader;
        p2shHeader = CoinDefinition.p2shHeader;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader};
        port = CoinDefinition.Port;
        packetMagic = CoinDefinition.PacketMagic;
        bip32HeaderPub = 0x0488B21E; //The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderPriv = 0x0488ADE4; //The 4 byte header that serializes in base58 to "xprv"
        genesisBlock.setDifficultyTarget(CoinDefinition.genesisBlockDifficultyTarget);
        genesisBlock.setTime(CoinDefinition.genesisBlockTime);
        genesisBlock.setNonce(CoinDefinition.genesisBlockNonce);

        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        id = ID_MAINNET;
        subsidyDecreaseBlockCount = CoinDefinition.subsidyDecreaseBlockCount;
        spendableCoinbaseDepth = CoinDefinition.spendableCoinbaseDepth;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals(CoinDefinition.genesisHash),
                genesisHash);

        //CoinDefinition.initCheckpoints(checkpoints);

        dnsSeeds = new String[] {
                "dnsseed.axe.org"
        };

        httpSeeds = null; /*new HttpDiscovery.Details[] {*/

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        checkpoints.put(  1000, Sha256Hash.wrap("000001c94f32e43dc459bc522e343b41f179b7b486e155e819c3f251ee4a6d8d"));
        checkpoints.put(  5000, Sha256Hash.wrap("0000002e22464e17a0140f686bfdd7794ade570f71d884098302cba16b6059d5"));
        checkpoints.put( 10000, Sha256Hash.wrap("00000000009994b68f4fb71d5e3d21fa92b259c41c7734e1b10ab7d8fcabe78b"));
        checkpoints.put( 12860, Sha256Hash.wrap("000000000002aae6fac2e55b094bcba9ded463d14bd81fa76f29623fa35d2261"));
        checkpoints.put( 13500, Sha256Hash.wrap("0000000000028510da80feb2ec0804f3fdbf62744ca5979d93f56514bbc45396"));
        checkpoints.put( 14385, Sha256Hash.wrap("000000000001af010a1dabf2dc45b4738c23453532c3c4d9b47903f8610a70b8"));
        checkpoints.put( 15120, Sha256Hash.wrap("000000000002eea846d5d97fa085ac6336d3e69832703c3ebeda89fdcfbcb7e4"));
        checkpoints.put( 20000, Sha256Hash.wrap("00000000000363c860c63cc8e9baf1cb93010385f697e91c538a4ea9df178a07"));
        checkpoints.put( 23965, Sha256Hash.wrap("00000000000bd575ee988f5768e931c4b8e3ca74d8f0fe0721ff9558e34944b7"));
        checkpoints.put( 27119, Sha256Hash.wrap("00000000000f139246bbe4022bec06387527d55972fd1a2619aaee5ed43b36c0"));
        checkpoints.put( 29425, Sha256Hash.wrap("0000000000033abb70490884e1bc0eac1d36341f1afe2e66a148b4ff6feec6bf"));
        checkpoints.put( 32749, Sha256Hash.wrap("00000000000c74050c24bdef66854a6489021316dd0bce146c9b1aba2755381c"));
        checkpoints.put( 40282, Sha256Hash.wrap("000000000018ea87170b4590c45b67624512125e45bd1711f8f89746d8af2904"));
        checkpoints.put( 52509, Sha256Hash.wrap("0000000000030ef66793b68d55e161dd7b8dc94c59a8c9ae42f757e38a1fa479"));
        checkpoints.put( 65392, Sha256Hash.wrap("000000000005668c10dc846ba6859cafe052ffa8b6d8a6888272fd84b45a95f0"));
        checkpoints.put( 71723, Sha256Hash.wrap("000000000021d50c7f8d564aa446e56c9ca63dacebdca74218c78dad73a1ea53"));
        checkpoints.put( 86382, Sha256Hash.wrap("00000000003badda6d1a8eeed581ccb67d6b556be55c5ec85e9a0329dfc56f74"));
        checkpoints.put( 93105, Sha256Hash.wrap("0000000000069b605e286b9c195bd400df907bd2fcf87398c2a0dfff5df0fbf3"));
        checkpoints.put( 100315, Sha256Hash.wrap("0000000000002f905a48b6b6e26d77ea86483d07f02081d2ab1de5f6279a0311"));
        checkpoints.put( 161083, Sha256Hash.wrap("0000000000092122485e390355bf4345ee51b0b14ba0d8c8ee6571ab41cca881"));
/*

        dnsSeeds = new String[] {
                "seed.bitcoin.sipa.be",         // Pieter Wuille
                "dnsseed.bluematt.me",          // Matt Corallo
                "dnsseed.bitcoin.axejr.org",   // Luke Axejr
                "seed.bitcoinstats.com",        // Chris Decker
                "seed.bitnodes.io",             // Addy Yeow
                "bitseed.xf2.org",              // Jeff Garzik
                "seed.bitcoin.jonasschnelli.ch",// Jonas Schnelli
                "bitcoin.bloqseeds.net",        // Bloq
        };
        httpSeeds = new HttpDiscovery.Details[] {
                // Andreas Schildbach
                new HttpDiscovery.Details(
                        ECKey.fromPublicOnly(Utils.HEX.decode("0238746c59d46d5408bf8b1d0af5740fe1a6e1703fcb56b2953f0b965c740d256f")),
                        URI.create("http://httpseed.bitcoin.schildbach.de/peers")
                )
        };                  */

        addrSeeds = new int[] {
                0x9d4e6505,
                0x6dbf8405,
                0xd4bf8405,
                0xd5bf8405,
                0xd9bf8405,
                0x25738605,
                0x2981bd05,
                0x40abbd05,
                0x9560dd12,
                0x827f6e17,
                0x927f6e17,
                0xed7f6e17,
                0x6f00af17,
                0x7000af17,
                0x7100af17,
                0x7200af17,
                0x1580b617,
                0x1780b617,
                0x1880b617,
                0x1980b617,
                0x4ca0e317,
                0x34a3e317,
                0x3d72661b,
                0x52491f1f,
                0x6360c422,
                0x4582c722,
                0x6165e122,
                0xa5c0a723,
                0xe3f2c423,
                0x80f73b25,
                0x91a17825,
                0xd2c79d25,
                0x2ac2dd25,
                0x2bc2dd25,
                0xdcc2dd25,
                0xf8b34728,
                0x2c067128,
                0x2e4de52b,
                0xb9ce202d,
                0x06404c2d,
                0xc36d1c2e,
                0xb19e252e,
                0x419a4a2f,
                0x0f33582f,
                0xf911592f,
                0x9c0e5a2f,
                0x5e42622f,
                0xcf60682f,
                0x8f2a0f33,
                0xabc90f33,
                0x24692633,
                0xfb802633,
                0xd520ff33,
                0x16704f34,
                0xbb24eb34,
                0x0825f234,
                0xc3e74636,
                0xa291ac36,
                0xd6038a3e,
                0x55fd8e3f,
                0x30da2240,
                0x729d8c40,
                0xe29f8c40,
                0x550bac42,
                0x5a0d3345,
                0x650d3345,
                0x660d3345,
                0x48143345,
                0x6bd51248,
                0xf8047f4b,
                0x94db4a4c,
                0xbe45494d,
                0x46cf294e,
                0x0013534e,
                0x46a9854e,
                0x961b7850,
                0xb691d350,
                0xdccda951,
                0xf502ab51,
                0x671da552,
                0x8315d352,
                0x8815d352,
                0x8b15d352,
                0x8c15d352,
                0x5160ea54,
                0x5260ea54,
                0x5660ea54,
                0x7dc81955,
                0xceaad955,
                0xf3abd955,
                0x4101ff55,
                0xd404ff55,
                0x37fd7557,
                0xf1dc2459,
                0x47902659,
                0x0e002859,
                0x3e002859,
                0x8a432d59,
                0x2ea12f59,
                0x4ab5ee59,
                0x52efdb5b,
                0x53efdb5b,
                0x50cd5a5d,
                0xd0d5685d,
                0x99d89e5d,
                0x88c4b15e,
                0xe1e0b15e,
                0x73e1b15e,
                0xd2e1b15e,
                0x21e8b15e,
                0x6ce9b15e,
                0x3dfab15e,
                0xa28b2b5f,
                0x2fe2b55f,
                0x6132b75f,
                0x8d33b75f,
                0x9233b75f,
                0x8635b75f,
                0xe12dd75f,
                0x390bd85f,
                0xf20ed85f,
                0x6d15d85f,
                0xa4607e60,
                0x00602565,
                0x60602565,
                0xa406df68,
                0xd135df68,
                0x89769b6b,
                0xd465bf6b,
                0x16e03d6c,
                0x21e03d6c,
                0x4c190a6e,
                0x76569f73,
                0xdaad727a,
                0xa640c17b,
                0x1136cf7b,
                0x16668285,
                0x20603b8b,
                0x520ba28b,
                0x2a324294,
                0x6d0aec97,
                0xf0397f9a,
                0x1b3b7f9a,
                0x083c7f9a,
                0x0c20599f,
                0xa613649f,
                0xa713649f,
                0x11c3dda2,
                0x90a72ca3,
                0xeda72ca3,
                0xbfa82ca3,
                0x7f5faca3,
                0x354aeba8,
                0xcb60eba8,
                0xcd60eba8,
                0x3ca24baa,
                0xdba24baa,
                0x6ca34baa,
                0x2ab151ac,
                0x947956ac,
                0x0d4068ac,
                0x62056eac,
                0xa9066eac,
                0x31d4d4ad,
                0x1da03eb2,
                0x1e32d1b2,
                0x072aeeb2,
                0x25ae16b9,
                0x5b651cb9,
                0x85651cb9,
                0xd94023b9,
                0x754323b9,
                0x7dd22bb9,
                0x71c23ab9,
                0xdd6840b9,
                0xde6840b9,
                0xdf6840b9,
                0x1e5577b9,
                0x9aed8bb9,
                0x90d48eb9,
                0x7db29cb9,
                0xd8b29cb9,
                0xdab29cb9,
                0xdeb29cb9,
                0x15a8a5b9,
                0x17a8a5b9,
                0x19a8a5b9,
                0x54a9a5b9,
                0x0d28b9b9,
                0xe575cbb9,
                0xfa2cd4b9,
                0x0125d5b9,
                0x0625d5b9,
                0x6301d9b9,
                0x6401d9b9,
                0x3070f3b9,
                0x5070f3b9,
                0xbb70f3b9,
                0xdd70f3b9,
                0x10bdfdb9,
                0x11bdfdb9,
                0x12bdfdb9,
                0x13bdfdb9,
                0x1c7a8abc,
                0x5845a6bc,
                0x5310e3bc,
                0x7210e3bc,
                0x3b12e3bc,
                0xb4b804be,
                0xfa080abe,
                0xe48251c0,
                0x68b6a1c0,
                0xc4e4e3c0,
                0x48bb1dc1,
                0x48e0eac1,
                0x64e0eac1,
                0x67e0eac1,
                0x91e0eac1,
                0xd9fafbc4,
                0x4b8017c6,
                0x29e217c6,
                0x0e4a35c6,
                0x8d6b37c6,
                0x8ebe3dc6,
                0x1dba8fc6,
                0xc36ec9c7,
                0xac807ac8,
                0x7b6247ca,
                0x7c6247ca,
                0x7d6247ca,
                0x7e6247ca,
                0x5758b1d1,
                0xcb5bb1d1,
                0x835cb1d1,
                0x4215bcd1,
                0x106018d4,
                0xfdeb2fd4,
                0xeb88e3d4,
                0x2fc9e3d4,
                0xf025edd4,
                0x5fc540d5,
                0x9f5088d5,
                0x93d1efd5,
                0x3ed96bd8,
                0xa191bdd8,
                0x35023dd9,
                0x6e859cdd
        };

        budgetPaymentsStartBlock = 328008;
        strSporkAddress = "PR8VqUyRm1Dm9tii6uv9D7gidWyA56SvqZ";

        DIP0001Window = MAINNET_MAJORITY_DIP0001_WINDOW;
        DIP0001Upgrade = MAINNET_MAJORITY_DIP0001_THRESHOLD;
        DIP0001BlockHeight = 782208;

        fulfilledRequestExpireTime = 60*60;
        masternodeMinimumConfirmations = 15;
        superblockStartBlock = 614820;
        superblockCycle = 16616;
        nGovernanceMinQuorum = 10;
        nGovernanceFilterElements = 20000;

        powDGWHeight = 34140;
        powKGWHeight = 15200;
        powAllowMinimumDifficulty = false;
        powNoRetargeting = false;

        instantSendConfirmationsRequired = 6;
        instantSendKeepLock = 24;
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }

    @Override
    protected void verifyDifficulty(StoredBlock storedPrev, Block nextBlock, BigInteger newTarget) {

        long newTargetCompact = calculateNextDifficulty(storedPrev, nextBlock, newTarget);
        long receivedTargetCompact = nextBlock.getDifficultyTarget();
        int height = storedPrev.getHeight() + 1;

        if (/*height >= powDGWHeight &&*/ height <= 68589) {
            double n1 = convertBitsToDouble(receivedTargetCompact);
            double n2 = convertBitsToDouble(newTargetCompact);

            if (java.lang.Math.abs(n1 - n2) > n1 * 0.5 )
                throw new VerificationException("Network provided difficulty bits do not match what was calculated: " +
                    Long.toHexString(newTargetCompact) + " vs " + Long.toHexString(receivedTargetCompact));
        } else {
            if (newTargetCompact != receivedTargetCompact)
                throw new VerificationException("Network provided difficulty bits do not match what was calculated: " +
                        Long.toHexString(newTargetCompact) + " vs " + Long.toHexString(receivedTargetCompact));
        }
    }

    static double convertBitsToDouble(long nBits) {
        long nShift = (nBits >> 24) & 0xff;

        double dDiff =
                (double)0x0000ffff / (double)(nBits & 0x00ffffff);

        while (nShift < 29)
        {
            dDiff *= 256.0;
            nShift++;
        }
        while (nShift > 29)
        {
            dDiff /= 256.0;
            nShift--;
        }

        return dDiff;
    }
}
