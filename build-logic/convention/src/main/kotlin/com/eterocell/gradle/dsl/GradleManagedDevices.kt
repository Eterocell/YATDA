package com.eterocell.gradle.dsl

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ManagedVirtualDevice
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.invoke

internal fun configureGradleManagedDevices(
    commonExtension: CommonExtension,
) {
    val pixel9 = DeviceConfig("Pixel 9", 35, "aosp-atd")

    val allDevices = listOf(pixel9)
    val ciDevices = listOf(pixel9)

    commonExtension.testOptions.apply {
        @Suppress("UnstableApiUsage")
        managedDevices.apply {
            allDevices {
                allDevices.forEach { deviceConfig ->
                    maybeCreate(deviceConfig.taskName, ManagedVirtualDevice::class.java).apply {
                        device = deviceConfig.device
                        apiLevel = deviceConfig.apiLevel
                        systemImageSource = deviceConfig.systemImageSource
                    }
                }
            }
            groups {
                maybeCreate("ci").apply {
                    ciDevices.forEach { deviceConfig ->
                        targetDevices.add(localDevices[deviceConfig.taskName])
                    }
                }
            }
        }
    }
}

private data class DeviceConfig(
    val device: String,
    val apiLevel: Int,
    val systemImageSource: String,
) {
    val taskName = buildString {
        append(device.lowercase().replace(" ", ""))
        append("api")
        append(apiLevel.toString())
        append(systemImageSource.replace("-", ""))
    }
}
